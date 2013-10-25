//
//  MainMenuInteractiveLayer.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/19/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "MainMenuInteractiveLayer.h"


@implementation MainMenuInteractiveLayer

#pragma mark -
#pragma mark Initialization

-(id)init
{
    self = [super init];
    if (self) {
        kBalloonBaseTag = 500;
        self.isTouchEnabled = YES;
        [self spawnBalloons:100];
        [self loadGameLabels];
        [self loadMenuItems];
    }
    return self;
}

- (void)loadGameLabels
{
    CCLabelBMFont *gameLabel = [CCLabelBMFont labelWithString:@"Pop The Balloons"
                                                         fntFile:kDefaultBitmapFontForPrompts];
    
    gameLabel.position = ccp(kScreenMiddleWidth, kScreenHeight - 175);
    
    [self addChild:gameLabel
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 10];
    
    id inflate1 = [CCScaleTo actionWithDuration:1.5f scale:1.15f];
    id delay = [CCDelayTime actionWithDuration:0.75f];
    id inflate2 = [CCScaleTo actionWithDuration:1.5f scale:2.5f];
    id explode = [CCCallBlockN actionWithBlock:^(CCNode *node){
        [[GameManager sharedGameManager] playSFX:kSfxBalloonPop];
        [[GameManager sharedGameManager] playSFX:kSfxBalloonDeflate];
        [self addConfettiParticlesOnPop:node];
    }];
    id deflate = [CCScaleTo actionWithDuration:1.35f scale:0.25f];
    id rotate = [CCRotateBy actionWithDuration:1.35f angle:720 * 2];
    id normalize = [CCScaleTo actionWithDuration:1.35f scale:1.0f];
    id moreActions = [CCSpawn actionWithArray:@[deflate, rotate, normalize]];
    id gameTitleSequence = [CCRepeatForever actionWithAction:[CCSequence actions:inflate1, delay, inflate2, delay, explode, moreActions, nil]];
    [gameLabel runAction:gameTitleSequence];
}

-(void)loadMenuItems
{

    CCLabelBMFont *startGameLabel = [CCLabelBMFont labelWithString:@"Start Game"
                                                           fntFile:kDefaultBitmapFontForControls];
    
    CCLabelBMFont *optionsGameLabel = [CCLabelBMFont labelWithString:@"Options"
                                                           fntFile:kDefaultBitmapFontForControls];
    
    
    CCMenuItemLabel *menuStartGame = [CCMenuItemLabel
                                      itemWithLabel:startGameLabel
                                      block:^(id sender) {
                                          [[GameManager sharedGameManager] playSFX:kSfxTap];
                                          [[CCDirector sharedDirector] replaceScene:[CCTransitionFade
                                                                                     transitionWithDuration:1.0
                                                                                     scene:[BalloonGame scene]]];
                                      }];
    
    CCMenuItemLabel *menuOptions = [CCMenuItemLabel
                                      itemWithLabel:optionsGameLabel
                                      block:^(id sender) {
                                          [[GameManager sharedGameManager] playSFX:kSfxTap];
                                      }];
    
    CCMenu *mainMenu = [CCMenu menuWithItems:menuStartGame, menuOptions, nil];
	[mainMenu alignItemsVertically];
    
	// add the menu to your scene
	[self addChild:mainMenu
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 20];
    
}

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
}

-(void)spawnBalloons:(NSInteger)quantity
{
    
    // Load texture atlas into the sprite frame cache
    CCSpriteBatchNode *BalloonGameSpriteBatchNode;
    [[CCSpriteFrameCache sharedSpriteFrameCache] addSpriteFramesWithFile:@"balloonGameScene.plist"];
    BalloonGameSpriteBatchNode = [CCSpriteBatchNode batchNodeWithFile:@"balloonGameScene.png"];
    
    [self addChild:BalloonGameSpriteBatchNode
                 z:kDefaultInteractiveLayerZValue
               tag:kDefaultInteractiveLayerTag];
    
    for (int i = 0; i < quantity; i++) {
        int color = (arc4random() % 6) + 1;
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
        }
        balloon.scale = 0.50f;
        balloon.userData = (__bridge void *)(colorName);
        int x = [CCDirector sharedDirector].winSize.width/2;
        int multiplier = (arc4random() % 2) + 1;
        multiplier = (multiplier == 1) ? 1 : -1;
        int factor = (arc4random() % x) * 1.25;
        int posX = x + (factor * multiplier);
        posX = (posX < 90) ? posX + 90 : posX > 320 ? 320 - 90 : posX;
        balloon.position = ccp(posX, -175);
        id delay = [CCDelayTime actionWithDuration:0.5 + i/2];
        id moveUp = [CCMoveTo actionWithDuration:1.75f
                                        position:ccp(posX, [CCDirector sharedDirector].winSize.height + 175)];
        id resetPosition = [CCMoveTo actionWithDuration:0
                                               position:ccp(posX, -175)];
        id moveBalloonSequence =[CCRepeatForever actionWithAction:[CCSequence actions:delay, moveUp, resetPosition, nil]];
        [self addChild:balloon
                     z:kDefaultButtonZValue + 1
                   tag:(kBalloonBaseTag + i)];
        [balloon runAction:moveBalloonSequence];
    }
}

@end
