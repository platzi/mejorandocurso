//
//  NyanCatInteractiveLayer.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "NyanCatInteractiveLayer.h"

@implementation NyanCatInteractiveLayer

#pragma mark -
#pragma mark Initialization

-(id)init
{
    self = [super init];
    if (self) {
        self.isTouchEnabled = YES;
        [self showStartDialog];
    }
    return self;
}

-(void)initGameState
{
    self.canFireLaser = YES;
    self.lastShotFired = 0;
    self.nyans = 0;
    self.deadAliens = 0;
    self.catHealth = 100;
    self.catState = kStateIdle;
    self.lasers = [NSMutableArray array];
    self.aliens = [NSMutableArray array];
    self.powerups = [NSMutableArray array];
    [self loadGameLabels];
}

-(void)showStartDialog
{
    [ModalAlert Tell:@"Get Ready to Nyan and kill Aliens"
             onLayer:self
             okBlock:^(void){
                 [self playMeowSFX];
                 [self launchGame];
    }];
}

-(void)showEndDialog
{
    [ModalAlert Ask:@"Want to Nyan Again?"
            onLayer:self
           yesBlock:^(void){
               [self playMeowSFX];
               [self launchGame];
           }
            noBlock:^(void){
                [self playAngryCatSFX];
                [self loadMainMenu];
            }];
}

-(void)launchGame
{
    [self initGameState];
    [self initJoystickAndButtons];
    [self loadBackgroundMusic];
    [self loadNyanCatAnimation];
    [self scheduleUpdate];
    [self schedule:@selector(spawnRobotAlien:) interval:0.5f];
    [self schedule:@selector(spawnPowerups:)
          interval:1.5f
            repeat:kCCRepeatForever
             delay:5.0f];
    [self schedule:@selector(incrementNyans:) interval:0.15f];
}

-(void)endGame
{
    [self playAngryCatSFX];
    id deathSpin = [CCRotateTo actionWithDuration:0.75f angle:1024];
    id deathFade = [CCFadeOut actionWithDuration:0.75f];
    id deathShrink = [CCScaleTo actionWithDuration:0.75f scale:0.01f];
    CCCallBlockN *explodingStars = [CCCallBlockN actionWithBlock:^(CCNode *node) {
        CGPoint explodingPoint = self.nyanCat.position;
        CCParticleSystemQuad *catplosion = [CCParticleSystemQuad particleWithFile:@"ccExplodingStarsParticles.plist"];
        catplosion.position = explodingPoint;
        catplosion.autoRemoveOnFinish = YES;
        [self addChild:catplosion];
    }];
    id deadCat = [CCSpawn actions:deathSpin, deathShrink, deathFade, explodingStars, nil];
    id delay = [CCDelayTime actionWithDuration:1.15f];
    CCCallBlockN *actionMoveDone = [CCCallBlockN actionWithBlock:^(CCNode *node) {
        [self removeAllChildrenWithCleanup:YES];
        [self unscheduleAllSelectors];
        [[GameManager sharedGameManager] stopAllSounds];
        [[GameManager sharedGameManager] stopBackgroundMusic];
        [self showEndDialog];
    }];
    id sequence = [CCSequence actions:deadCat, delay, actionMoveDone, nil];
    [self.nyanCat runAction:sequence];
}

- (void)initJoystickAndButtons
{
    CGSize screenSize = [CCDirector sharedDirector].winSize;
    CGRect joystickBaseDimensions = CGRectMake(0, 0, 128.0f, 128.0f);
    CGRect fireButtonDimensions = CGRectMake(0, 0, 64.0f, 64.0f);
    CGPoint joystickBasePosition;
    CGPoint fireButtonPosition;
    
    CCLOG(@"Positioning Joystick and Buttons for iPhone");
    joystickBasePosition = ccp(screenSize.width*0.07f,
                               screenSize.height*0.11f);
    
    fireButtonPosition = ccp(screenSize.width*0.93f,
                             screenSize.height*0.11f);
        
    
    SneakyJoystickSkinnedBase *joystickBase = [[SneakyJoystickSkinnedBase alloc] init];
    joystickBase.position = joystickBasePosition;
    joystickBase.backgroundSprite = [CCSprite spriteWithFile:@"dpadBase.png"];
    joystickBase.thumbSprite = [CCSprite spriteWithFile:@"dpadStick.png"];
    joystickBase.joystick = [[SneakyJoystick alloc] initWithRect:joystickBaseDimensions];
    self.joystick = joystickBase.joystick;
    [self addChild:joystickBase];
    
    SneakyButtonSkinnedBase *fireButtonBase = [[SneakyButtonSkinnedBase alloc] init];
    fireButtonBase.position = fireButtonPosition;
    fireButtonBase.defaultSprite = [CCSprite spriteWithFile:@"fireButton_up.png"];
    fireButtonBase.activatedSprite = [CCSprite spriteWithFile:@"fireButton_down.png"];
    fireButtonBase.pressSprite = [CCSprite spriteWithFile:@"fireButton_down.png"];
    fireButtonBase.button = [[SneakyButton alloc] initWithRect:fireButtonDimensions];
    fireButtonBase.button.isToggleable = NO;
    self.fireButton = fireButtonBase.button;
    [self addChild:fireButtonBase];
    
}

- (void)loadBackgroundMusic
{
    NSInteger song = (arc4random() % 4) + 1;
    NSString *songName;
    
    switch (song) {
        case 1:
            songName = kBackgroundTrack1;
            break;
            
        case 2:
            songName = kBackgroundTrack2;
            break;
            
        case 3:
            songName = kBackgroundTrack3;
            break;
            
        case 4:
            songName = kBackgroundTrack4;
            break;
            
        default:
            song = kBackgroundTrack1;
            break;
    }
    
    [[GameManager sharedGameManager] playBackgroundMusic:songName loop:YES];
}

- (void)loadNyanCatAnimation
{
    [[CCSpriteFrameCache sharedSpriteFrameCache] addSpriteFramesWithFile:@"nyanCatTextureAtlas.plist"];
    CCSpriteBatchNode *spriteSheet = [CCSpriteBatchNode batchNodeWithFile:@"nyanCatTextureAtlas.png"];
    
    [self addChild:spriteSheet
                 z:kDefaultInteractiveLayerZValue
               tag:kDefaultInteractiveLayerTag];
    
    NSMutableArray *catAnimFrames = [NSMutableArray array];
    for (int i = 17; i <= 27; i++) {
        [catAnimFrames addObject:[[CCSpriteFrameCache sharedSpriteFrameCache]
                                  spriteFrameByName:[NSString stringWithFormat:@"%d.png", i]]];
    }
    
    CCAnimation *catAnim = [CCAnimation animationWithSpriteFrames:catAnimFrames delay:0.1f];
    
    CCSprite *nyanCatAnim = [CCSprite spriteWithSpriteFrameName:@"17.png"];
    nyanCatAnim.position = ccp(25.0f, kScreenMiddleHeight);
    id catAction = [CCRepeatForever actionWithAction:[CCAnimate actionWithAnimation:catAnim]];
    [nyanCatAnim runAction:catAction];
    [self addChild:nyanCatAnim
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + kDefaultNyanCatTag];
    self.nyanCat = nyanCatAnim;
}

-(void)loadGameLabels
{
    CCLabelBMFont *nyansLabel = [CCLabelBMFont labelWithString:@"nyans :"
                                                      fntFile:kDefaultBitmapFontForPrompts];
    
    CCLabelBMFont *healthLabel = [CCLabelBMFont labelWithString:@"health : 100%"
                                                       fntFile:kDefaultBitmapFontForPrompts];
    
    CCLabelBMFont *deadAliensLabel = [CCLabelBMFont labelWithString:@"aliens : 0"
                                                        fntFile:kDefaultBitmapFontForPrompts];
    
    nyansLabel.position = ccp(kScreenMiddleWidth, kScreenHeight - 18.0f);
    healthLabel.position = ccp(60.0f, kScreenHeight - 18.0f);
    deadAliensLabel.position = ccp(kScreenWidth - 60.0f, kScreenHeight - 18.0f);
    
    healthLabel.scale = 0.45;
    deadAliensLabel.scale = 0.45;
    
    [self addChild:nyansLabel
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 10];
    
    [self addChild:healthLabel
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 20];
    
    [self addChild:deadAliensLabel
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 30];
}

-(void)loadMainMenu
{
    [[CCDirector sharedDirector] replaceScene:[CCTransitionFade
                                               transitionWithDuration:1.0
                                               scene:[MainMenu scene]]];
}

#pragma mark -
#pragma mark Scheduled Update

-(void)update:(ccTime)delta
{
    [self detectAlienHit];
    [self detectCatHit];
    [self applyJoystickMovement:self.joystick
                           node:self.nyanCat
                  withDeltaTime:delta];
    [self queryFireButton:delta];
}

-(void)queryJoystickAndFireButton
{
    if (self.joystick.velocity.x > 0 ){
        NSLog(@"Mueves hacia la derecha: %f", self.joystick.velocity.x);
    } else if ( self.joystick.velocity.x < 0 ) {
        NSLog(@"Mueves hacia la izquierda %f", self.joystick.velocity.x);
    }
    
    if (self.joystick.velocity.y > 0 ){
        NSLog(@"Mueves hacia arriba %f", self.joystick.velocity.y);
    } else if (self.joystick.velocity.y < 0 ){
        NSLog(@"Mueves hacia abajo %f", self.joystick.velocity.y);
    }
}

-(void)applyJoystickMovement:(SneakyJoystick*)joystick
                        node:(CCNode*)aNode
               withDeltaTime:(float)deltaTime
{
    
    if(joystick.velocity.x != 0 && joystick.velocity.y != 0){
        if(self.catState == kStateIdle){
            [self changeCatState:kStateFlying];
        }
    } else{
        [self changeCatState:kStateIdle];
    }
    
    CGPoint scaledVelocity = ccpMult(joystick.velocity, 384.0f);
    
    float newPosX = aNode.position.x + scaledVelocity.x * deltaTime;
    float newPosY = aNode.position.y + scaledVelocity.y * deltaTime;
    
    newPosX = newPosX > kScreenMiddleWidth && self.catState != kStateDead ? kScreenMiddleWidth : newPosX;
    newPosX = newPosX < 25.0f ? 25.0f : newPosX;
    
    newPosY = newPosY > kScreenHeight - 15.0f ? kScreenHeight - 15.0f : newPosY;
    newPosY = newPosY < 15.0f ? 15.0f : newPosY;
    
    CGPoint newPosition = ccp(newPosX, newPosY);
    [aNode setPosition:newPosition];
}

-(void)queryFireButton:(float)deltaTime
{
    if(self.canFireLaser == NO){
        self.lastShotFired += deltaTime;
        self.canFireLaser = self.lastShotFired > 0.5f ? YES : NO;
    }

    if (self.fireButton.active && self.canFireLaser){
        CCLOG(@"Firing");
        self.canFireLaser = NO;
        self.lastShotFired = 0;
        [self shootLaser];
    }else if (self.fireButton.active && self.canFireLaser == NO){
        self.lastShotFired += deltaTime;
    } else{
        CCLOG(@"Inactive");
    }
}

-(void)spawnGameObject:(ccTime)dt objectType:(GameObjectType)type
{
    CCSprite *gameObject;
    NSInteger kGameObjectBaseType = 101;
    NSInteger randomObjectType = (arc4random() % 4) + 1;
    NSInteger i = (arc4random() % 100) + 1;
    
    switch (randomObjectType) {
        case 1:
            gameObject = type == kEnemyTypeAlienRobot ?
                [CCSprite spriteWithSpriteFrameName:@"invader1.png"] :
                [CCSprite spriteWithSpriteFrameName:@"powerup-1.png"];
            break;
        case 2:
            gameObject = type == kEnemyTypeAlienRobot ?
                [CCSprite spriteWithSpriteFrameName:@"invader2-1.png"] :
                [CCSprite spriteWithSpriteFrameName:@"powerup-2.png"];
            break;
        case 3:
            gameObject = type == kEnemyTypeAlienRobot ?
                [CCSprite spriteWithSpriteFrameName:@"invader3-1.png"] :
                [CCSprite spriteWithSpriteFrameName:@"powerup-3.png"];
            break;
        case 4:
            gameObject = type == kEnemyTypeAlienRobot ?
                [CCSprite spriteWithSpriteFrameName:@"invader4-1.png"] :
                [CCSprite spriteWithSpriteFrameName:@"powerup-4.png"];
            break;
    }

    gameObject.scale = 0.75f;
    gameObject.userData = type;
    
    NSInteger y = (arc4random() % (NSInteger)kScreenHeight - 10) + 10;
    NSInteger x = kScreenWidth + 200;
    
    NSInteger slowFactor = (arc4random() & 2) + 0.25;
    
    gameObject.position = ccp(x, y);
    id delay = [CCDelayTime actionWithDuration:0.5 + i/2];
    id moveLeft = [CCMoveTo actionWithDuration:(2.25f + slowFactor)
                                      position:ccp(-200, y)];
    
    CCCallBlockN * actionMoveDone = [CCCallBlockN actionWithBlock:^(CCNode *node) {
        if((int)node.userData == kEnemyTypeAlienRobot){
            [self.aliens removeObject:node];
        } else {
            [self.powerups removeObject:node];
        }
        [node removeFromParentAndCleanup:YES];
    }];

    [self addChild:gameObject
                 z:kDefaultButtonZValue + 1
               tag:(kGameObjectBaseType + i)];
    
    if(type == kEnemyTypeAlienRobot){
        [self.aliens addObject:gameObject];
    } else {
        [self.powerups addObject:gameObject];
    }
    
    [gameObject runAction:[CCSequence actions:delay, moveLeft, actionMoveDone, nil]];

}

-(void)spawnRobotAlien:(ccTime)dt
{
    [self spawnGameObject:dt objectType:kEnemyTypeAlienRobot];
}

-(void)spawnPowerups:(ccTime)dt
{
    [self spawnGameObject:dt objectType:kPowerUpType];
}

-(void)detectCatHit
{
    for (CCSprite *alien in self.aliens) {
        if(self.catState == kStateFlying || self.catState == kStateIdle){
            if (CGRectIntersectsRect(self.nyanCat.boundingBox, alien.boundingBox)) {
                [self changeCatState:kStateTakingDamage];
                [self hitNyanCat];
                self.catHealth -= 10;
                [self updateCatHealthWithHealth:self.catHealth];
                if(self.catHealth <= 0){
                    self.catState = kStateDead;
                    [self endGame];
                }
            }
        }
    }
    
    for (CCSprite *powerup in self.powerups) {
        if(self.catState == kStateFlying || self.catState == kStateIdle){
            if (CGRectIntersectsRect(self.nyanCat.boundingBox, powerup.boundingBox)) {
                [self playPowerupSFX];
                self.catHealth += 10;
                self.catHealth = self.catHealth > 100 ? 100 : self.catHealth;
                [self updateCatHealthWithHealth:self.catHealth];
            }
        }
    }
    
}

-(void)detectAlienHit
{
    NSMutableArray *lasersToDelete = [[NSMutableArray alloc] init];
    for (CCSprite *laser in self.lasers) {
        
        NSMutableArray *aliensToDelete = [[NSMutableArray alloc] init];
        for (CCSprite *alien in self.aliens) {
            
            if (CGRectIntersectsRect(laser.boundingBox, alien.boundingBox)) {
                [aliensToDelete addObject:alien];
                [self playAlienExplosion];
                [self showAlienExplosion:laser.position];
                self.deadAliens++;
                [self updateDeadAliensWithScore:self.deadAliens];
            }
        }
        
        for (CCSprite *hitAlien in aliensToDelete) {
            [self.aliens removeObject:hitAlien];
            [self removeChild:hitAlien cleanup:YES];
        }
        
        if (aliensToDelete.count > 0) {
            [lasersToDelete addObject:laser];
        }
    }
    
    for (CCSprite *laserOnAlien in lasersToDelete) {
        [self.lasers removeObject:laserOnAlien];
        [self removeChild:laserOnAlien cleanup:YES];
    }
}

-(void)changeCatState:(CharacterStates)state
{
    self.catState = state;
}

-(void)hitNyanCat
{
    [self playMeowSFX];
    id delay = [CCDelayTime actionWithDuration:0.10];
    id colorAction = [CCTintTo actionWithDuration:0.05 red:255 green:0 blue:0];
    id resetColorAction = [CCTintTo actionWithDuration:0.05 red:255 green:255 blue:255];
    id sequence = [CCSequence actions:colorAction, delay, resetColorAction, delay, colorAction, delay, resetColorAction, nil];
    CCCallBlockN *resetCatState = [CCCallBlockN actionWithBlock:^(CCNode *node) {
        [self changeCatState:kStateIdle];
    }];
    id sequences = [CCSequence actions:sequence, sequence, sequence, resetCatState, nil];
    [self.nyanCat runAction:sequences];
}

-(void)powerupNyanCat
{
    [self playMeowSFX];
}

-(void)shootLaser
{
    CGPoint laserStartPoint = self.nyanCat.position;
    CCParticleSystemQuad *laserShot = [CCParticleSystemQuad particleWithFile:@"ccLaserShotParticle.plist"];
    laserShot.position = laserStartPoint;
    laserShot.autoRemoveOnFinish = NO;
    id moveRight = [CCMoveTo actionWithDuration:1.25f
                                       position:ccp(kScreenWidth + 20, laserStartPoint.y)];
    CCCallBlockN * actionMoveDone = [CCCallBlockN actionWithBlock:^(CCNode *node) {
        [self.lasers removeObject:node];
        [node removeFromParentAndCleanup:YES];
    }];
    [self addChild:laserShot
                 z:kDefaultInteractiveLayerZValue - 2
               tag:kDefaultInteractiveLayerTag];
    [self.lasers addObject:laserShot];
    [laserShot runAction:[CCSequence actions:moveRight, actionMoveDone, nil]];
    [self playFireButtonSFX];
}

-(void)showAlienExplosion:(CGPoint)position
{
    CCParticleSystemQuad *explosion = [CCParticleSystemQuad particleWithFile:@"ccAlienExplosionParticles.plist"];
    explosion.position = position;
    explosion.autoRemoveOnFinish = YES;
    [self addChild:explosion];
}

-(void)incrementNyans:(ccTime)dt
{
    self.nyans++;
    [self updateScoreWithPoints:self.nyans];
}

-(void)updateScoreWithPoints:(NSInteger)points
{
    CCLabelBMFont *nyans = (CCLabelBMFont*)[self getChildByTag:kDefaultInteractiveLayerTag + 10];
    NSString *nyansText = [NSString stringWithFormat:@"nyans: %d", points];
    [nyans setString:nyansText];
}

-(void)updateCatHealthWithHealth:(NSInteger)health
{
    CCLabelBMFont *catHealth = (CCLabelBMFont*)[self getChildByTag:kDefaultInteractiveLayerTag + 20];
    NSString *healthText = [NSString stringWithFormat:@"health: %d%%", health];
    [catHealth setString:healthText];
}

-(void)updateDeadAliensWithScore:(NSInteger)score
{
    CCLabelBMFont *deadAliens = (CCLabelBMFont*)[self getChildByTag:kDefaultInteractiveLayerTag + 30];
    NSString *deadAliensText = [NSString stringWithFormat:@"aliens: %d", score];
    [deadAliens setString:deadAliensText];
}


#pragma mark -
#pragma mark Sounds

-(void)playMeowSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxTap];
}

-(void)playPowerupSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxCoin1];
}

-(void)playAngryCatSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxAngryCat];
}

-(void)playFireButtonSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxLaser];
}

-(void)playAlienExplosion
{
    [[GameManager sharedGameManager] playSFX:kSfxExplosion2];
   
}

@end
