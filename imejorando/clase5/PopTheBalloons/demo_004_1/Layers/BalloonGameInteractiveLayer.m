//
//  BalloonGameInteractiveLayer.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "BalloonGameInteractiveLayer.h"

@implementation BalloonGameInteractiveLayer(private)
    NSInteger kBalloonBaseTag = 500;
    NSInteger kOKScore = 15;
    NSInteger kGoodScore = 16;
    NSInteger kGreatScore = 30;
@end

@implementation BalloonGameInteractiveLayer

#pragma mark -
#pragma mark Initialization

-(id)init
{
    self = [super init];
    if (self) {
        self.isTouchEnabled = YES;
        [self loadSpriteElements];
        [self animateClouds];
        [self loadAudio];
    }
    return self;
}

-(void)loadSpriteElements
{
    // Load texture atlas into the sprite frame cache
    CCSpriteBatchNode *BalloonGameSpriteBatchNode;
    [[CCSpriteFrameCache sharedSpriteFrameCache] addSpriteFramesWithFile:@"balloonGameScene.plist"];
    BalloonGameSpriteBatchNode = [CCSpriteBatchNode batchNodeWithFile:@"balloonGameScene.png"];
    
    // Add Clouds
    CCSprite *cloud1 = [CCSprite spriteWithSpriteFrameName:@"cloud1.png"];
    cloud1.position = ccp(kOffScreenPosition, kOffScreenPosition);
    [BalloonGameSpriteBatchNode addChild:cloud1
                                       z:kDefaultButtonZValue
                                     tag:kCloudBaseTag];
    
    CCSprite *cloud2 = [CCSprite spriteWithSpriteFrameName:@"cloud2.png"];
    cloud2.position = ccp(kOffScreenPosition, kOffScreenPosition);
    [BalloonGameSpriteBatchNode addChild:cloud2
                                       z:kDefaultButtonZValue
                                     tag:kCloudBaseTag + 1];
    
    CCSprite *cloud3 = [CCSprite spriteWithSpriteFrameName:@"cloud1.png"];
    cloud3.position = ccp(kOffScreenPosition, kOffScreenPosition);
    [BalloonGameSpriteBatchNode addChild:cloud3
                                       z:kDefaultButtonZValue
                                     tag:kCloudBaseTag + 2];
    
    
    // Add Balloons
    [self addChild:BalloonGameSpriteBatchNode
                 z:kDefaultInteractiveLayerZValue
               tag:kDefaultInteractiveLayerTag];
    
    // Add Time and Score Labels
    [self loadTimeAndScoreLabels];
    
    [self spawnBalloons:kBalloonGameMaxBalloons/2];

}

- (void)loadTimeAndScoreLabels
{
    /*
    CCLabelTTF *score = [CCLabelTTF labelWithString:@"Score: "
                                           fontName:@"Noteworthy-Light"
                                           fontSize:15.0f];
     */
    
    CCLabelBMFont *score = [CCLabelBMFont labelWithString:@"Score: "
                                                  fntFile:kDefaultBitmapFontForControls];
    
    /*
    CCLabelTTF *time = [CCLabelTTF labelWithString:@"Time: "
                                           fontName:@"Noteworthy-Light"
                                           fontSize:15.0f];
     */
    
    CCLabelBMFont *time = [CCLabelBMFont labelWithString:@"Time: "
                                                  fntFile:kDefaultBitmapFontForControls];
    
    
    [score setScale:0.75f];
    [time setScale:0.75f];
    
    score.position = ccp(kScreenWidth - score.contentSize.width * 2, kScreenHeight - (score.contentSize.height/2) - 15);
    time.position = ccp(kScreenMiddleWidth/2 + time.contentSize.width * 2 - 15, kScreenHeight - (time.contentSize.height/2) - 15);
    
    [self addChild:score
                 z:kDefaultInteractiveLayerZValue + 10
               tag:kScoreLabelTag];
    
    [self addChild:time
                 z:kDefaultInteractiveLayerZValue + 10
               tag:kTimeLabelTag];
    
    [self updateScoreWithPoints:0];
}

-(void)animateClouds
{
    id batchNode = [self getChildByTag:kDefaultInteractiveLayerTag];
    CCNode *cloud1 = [batchNode getChildByTag:kCloudBaseTag];
    CCNode *cloud2 = [batchNode getChildByTag:kCloudBaseTag + 1];
    CCNode *cloud3 = [batchNode getChildByTag:kCloudBaseTag + 2];
    cloud1.position = ccp(-35, [CCDirector sharedDirector].winSize.height/2 + 65);
    cloud2.position = ccp(520, [CCDirector sharedDirector].winSize.height - 95);
    cloud3.position = ccp(420, [CCDirector sharedDirector].winSize.height - 45);
    
    id moveCloud1Right = [CCMoveTo actionWithDuration:15.5f position:ccp(520, [CCDirector sharedDirector].winSize.height/2 + 65)];
    id moveCloud1Left = [CCMoveTo actionWithDuration:15.5f position:ccp(-35, [CCDirector sharedDirector].winSize.height/2 + 65)];
    id moveCloud1Sequence = [CCRepeatForever actionWithAction:[CCSequence actions:moveCloud1Right, moveCloud1Left, nil]];
    
    id moveCloud2Right = [CCMoveTo actionWithDuration:10.5f position:ccp(-35, [CCDirector sharedDirector].winSize.height - 95)];
    id moveCloud2Left = [CCMoveTo actionWithDuration:10.5f position:ccp(520, [CCDirector sharedDirector].winSize.height - 95)];
    id moveCloud2Sequence = [CCRepeatForever actionWithAction:[CCSequence actions:moveCloud2Right, moveCloud2Left, nil]];
    
    id moveCloud3Right = [CCMoveTo actionWithDuration:12.5f position:ccp(-35, [CCDirector sharedDirector].winSize.height - 45)];
    id moveCloud3Left = [CCMoveTo actionWithDuration:12.5f position:ccp(420, [CCDirector sharedDirector].winSize.height- 45)];
    id moveCloud3Sequence = [CCRepeatForever actionWithAction:[CCSequence actions:moveCloud3Right, moveCloud3Left, nil]];
    
    [cloud1 runAction:moveCloud1Sequence];
    [cloud2 runAction:moveCloud2Sequence];
    [cloud3 runAction:moveCloud3Sequence];
}

-(void)spawnBalloons:(NSInteger)quantity
{
    for (int i = 0; i < quantity; i++) {
        int color = (arc4random() % 7) + 1;
        NSString* colorName;
        CCSprite *balloon;
        switch (color) {
            case 1:
                balloon = [CCSprite spriteWithSpriteFrameName:@"blueBalloon.png"];
                colorName = @"Blue";
                break;
            case 2:
                balloon = [CCSprite spriteWithSpriteFrameName:@"greenBalloon.png"];
                colorName = @"Green";
                break;
            case 3:
                balloon = [CCSprite spriteWithSpriteFrameName:@"orangeBalloon.png"];
                colorName = @"Orange";
                break;
            case 4:
                balloon = [CCSprite spriteWithSpriteFrameName:@"purpleBalloon.png"];
                colorName = @"Purple";
                break;
            case 5:
                balloon = [CCSprite spriteWithSpriteFrameName:@"redBalloon.png"];
                colorName = @"Red";
                break;
            case 6:
                balloon = [CCSprite spriteWithSpriteFrameName:@"yellowBalloon.png"];
                colorName = @"Yellow";
                break;
            case 7:
                balloon = [CCSprite spriteWithFile:@"bomb.png"];
                balloon.rotation = 50;
                colorName = @"Bomb";
                break;
        }
        balloon.scale = 0.50f;
        balloon.userData = (__bridge void *)(colorName);
        
        NSInteger posX = (arc4random() % (NSInteger)kScreenWidth - 10) + 10;
        NSInteger posY = kScreenHeight + 175;
        
        balloon.position = ccp(posX, posY);
        
        id delay = [CCDelayTime actionWithDuration:0.5 + i/2];
        id moveUp = [CCMoveTo actionWithDuration:1.75f
                                        position:ccp(posX, kScreenHeight + 175)];
        id resetPosition = [CCMoveTo actionWithDuration:0
                                               position:ccp(posX, -175)];
        id moveBalloonSequence =[CCRepeatForever actionWithAction:[CCSequence actions:delay, moveUp, resetPosition, nil]];
        [self addChild:balloon
                     z:kDefaultButtonZValue + 1
                   tag:(kBalloonBaseTag + i)];
        [balloon runAction:moveBalloonSequence];
        
    }
}

-(void)removeBalloons:(NSInteger)quantity
{
    for (int i = 0; i < quantity; i++) {
        [self removeChildByTag:(kBalloonBaseTag + i) cleanup:YES];
    }
}

-(void)loadGame
{
    NSString *launchGameMessage = [NSString stringWithFormat:@"Pop all the Balloons in %d seconds, avoid the bombs\r\nGet Ready!!!", kDefaultGameClockInSeconds];
    [ModalAlert Tell:launchGameMessage onLayer:self okBlock:^{
        [[GameManager sharedGameManager] playSFX:kSfxTap];
        [self launchGame];
    }];
}

-(void)launchGame
{
    [self removeBalloons:kBalloonGameMaxBalloons/2];
    self.score = 0;
    self.gameClock = kDefaultGameClockInSeconds;
    [self updateScoreWithPoints:self.score];
    [self spawnBalloons:kBalloonGameMaxBalloons];
    [self playBackgroundMusic];
    [self schedule:@selector(updateGameTimer) interval:1.0f];
}

-(void)loadMainMenu
{
    [[CCDirector sharedDirector] replaceScene:[CCTransitionFade
                                               transitionWithDuration:1.0
                                               scene:[MainMenu scene]]];
}

#pragma mark -
#pragma mark scene events

- (void)onEnterTransitionDidFinish
{
    [self loadGame];
}

#pragma mark -
#pragma mark touch event

-(void)ccTouchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
{
    [self hasBalloonBeenTouched:touches];
    [self hasCloudBeenTouched:touches];
}

-(void)hasBalloonBeenTouched:(NSSet *)touches
{
    for (int i = 0; i < kBalloonGameMaxBalloons; i++) {
        CCNode *balloon = (CCNode*)[self getChildByTag:(kBalloonBaseTag + i)];
        if(balloon != nil){
            if([CCNode touched:touches node:balloon]){
                
                // Check to see if it is bomb
                NSString *isBomb = (NSString*)balloon.userData;
                
                if([isBomb isEqualToString:@"Bomb"]){
                    [self playExplosionSFX];
                    [self addLargeExplosionParticlesOnPop:balloon];
                    self.score--;
                    self.score = self.score < 0 ? 0 : self.score;
                } else{
                    // Add Sound Effect
                    [self playBalloonPopSFX];
                    // Add particle explosion
                    [self addConfettiParticlesOnPop:balloon];
                    self.score++;
                }
                
                [self updateScoreWithPoints:self.score];
                [self removeChild:balloon cleanup:YES];
                break;
            }
        }
    }
    
}

-(void)updateScoreWithPoints:(NSInteger)points
{
    CCLabelBMFont *score = (CCLabelBMFont*)[self getChildByTag:kScoreLabelTag];
    NSString *scoreText = [NSString stringWithFormat:@"Score: %d", points];
    [score setString:scoreText];
}

-(void)hasCloudBeenTouched:(NSSet *)touches
{
    CCNode *gameBatchNode = (CCNode*)[self getChildByTag:kDefaultInteractiveLayerTag];
    
    CCNode *cloud0 = (CCNode*)[gameBatchNode getChildByTag:kCloudBaseTag];
    CCNode *cloud1 = (CCNode*)[gameBatchNode getChildByTag:(kCloudBaseTag + 1)];
    CCNode *cloud2 = (CCNode*)[gameBatchNode getChildByTag:(kCloudBaseTag + 2)];
    
    if([CCNode touched:touches node:cloud0] ||
       [CCNode touched:touches node:cloud1] ||
       [CCNode touched:touches node:cloud2]){
        [[GameManager sharedGameManager] playSFX:kSfxCloudTap];
    }
    
}

#pragma mark -
#pragma mark background music and SFXs

-(void)loadAudio
{
    
    [CDSoundEngine setMixerSampleRate:CD_SAMPLE_RATE_MID];
    [[CDAudioManager sharedManager] setResignBehavior:kAMRBStopPlay
                                           autoHandle:YES];
}

-(void)playBackgroundMusic
{
    int nbr = (arc4random() % 3) + 1;
    switch (nbr) {
        case 1:
            [[GameManager sharedGameManager] playBackgroundMusic:kBackgroundTrack1
                                                            loop:YES];
            break;
            
        case 2:
            [[GameManager sharedGameManager] playBackgroundMusic:kBackgroundTrack2
                                                            loop:YES];
            break;
            
        case 3:
            [[GameManager sharedGameManager] playBackgroundMusic:kBackgroundTrack3
                                                            loop:YES];
            break;
            
        default:
            [[GameManager sharedGameManager] playBackgroundMusic:kBackgroundTrack1
                                                            loop:YES];
            break;
    }
}

-(void)playBalloonPopSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxBalloonPop];
}

-(void)playExplosionSFX
{
    [[GameManager sharedGameManager] playSFX:kSfxExplosion];
}


#pragma mark -
#pragma mark particle system

-(void)addConfettiParticlesOnPop:(CCNode*)balloon
{
    CCParticleSystemQuad *fwBlue = [CCParticleSystemQuad particleWithFile:@"ccParticleConfettiBlue.plist"];
    CCParticleSystemQuad *fwRed = [CCParticleSystemQuad particleWithFile:@"ccParticleConfettiRed.plist"];
    CCParticleSystemQuad *fwOrange = [CCParticleSystemQuad particleWithFile:@"ccParticleConfettiOrange.plist"];
    CCParticleSystemQuad *fwGreen = [CCParticleSystemQuad particleWithFile:@"ccParticleConfettiGreen.plist"];
    fwBlue.autoRemoveOnFinish = YES;
    fwBlue.position = balloon.position;
    fwRed.autoRemoveOnFinish = YES;
    fwRed.position = balloon.position;
    fwOrange.autoRemoveOnFinish = YES;
    fwOrange.position = balloon.position;
    fwGreen.autoRemoveOnFinish = YES;
    fwGreen.position = balloon.position;
    [self addChild:fwBlue z:balloon.zOrder + 1 tag:690];
    [self addChild:fwRed z:balloon.zOrder + 1 tag:691];
    [self addChild:fwOrange z:balloon.zOrder + 1 tag:692];
    [self addChild:fwGreen z:balloon.zOrder + 1 tag:693];
    [self removeChild:balloon cleanup:YES];
}

-(void)addLargeExplosionParticlesOnPop:(CCNode*)balloon
{
    CCParticleSystemQuad *largeExplosion = [CCParticleSystemQuad particleWithFile:@"ccParticleExplosion.plist"];
    largeExplosion.autoRemoveOnFinish = YES;
    largeExplosion.position = balloon.position;
    [self addChild:largeExplosion z:balloon.zOrder + 10 tag:9999];
    [self removeChild:balloon cleanup:YES];
}

#pragma mark -
#pragma mark game timer

-(void)updateGameTimer
{
    self.gameClock--;
    [self updateGameClockWithSeconds:self.gameClock];
    if(self.gameClock == 0){
        [self unschedule:@selector(updateGameTimer)];
        [self stopAllActions];
        [self removeBalloons:kBalloonGameMaxBalloons];
        [self showGameCompletionAlert];
    } else if (self.gameClock == 5 ||
               self.gameClock == 4 ||
               self.gameClock == 3 ||
               self.gameClock == 2 ||
               self.gameClock == 1){
        [[GameManager sharedGameManager] playSFX:kSfxClockTick];
    }
}

-(void)updateGameClockWithSeconds:(NSInteger)sec
{
    CCLabelTTF *time = (CCLabelTTF*)[self getChildByTag:kTimeLabelTag];
    NSString *timeText = [NSString stringWithFormat:@"Time: %d", sec];
    [time setString:timeText];
}

#pragma mark -
#pragma mark alert

-(void)showGameCompletionAlert
{
    NSString *completionMessage;
    NSString *completionSound;
    
    if (self.score <= kOKScore) {
        completionMessage = @"EPIC FAIL!\r\nYou Lose\r\nGood Day Sir!";
        completionSound = kSFXFail2;
    }
    
    if (self.score >= kGoodScore && self.score <= kGreatScore) {
        completionMessage = [NSString stringWithFormat:@"Not Bad, Not Bad\r\nTop %d for an EPIC WIN", kGreatScore];
        completionSound = kSFXWin1;
    }
    
    if (self.score >= kGreatScore) {
        completionMessage = @"EPIC WIN!\r\nYou gotta an EPIC WIN, now keep playing";
        completionSound = kSFXWin2;
    }
    
    [[GameManager sharedGameManager] stopBackgroundMusic];
    [[GameManager sharedGameManager] playSFX:completionSound];
    [ModalAlert Tell:completionMessage onLayer:self okBlock:^{
        [[GameManager sharedGameManager] playSFX:kSfxTap];
        NSString *again = @"Want to Play Again?";
        [ModalAlert Ask:again
                onLayer:self
               yesBlock:^{
                   [[GameManager sharedGameManager] playSFX:kSfxTap];
                   [self loadGame];
               }
                noBlock:^{
                    [[GameManager sharedGameManager] playSFX:kSfxTap];
                    [self loadMainMenu];
                }];
    }];
    
}

@end
