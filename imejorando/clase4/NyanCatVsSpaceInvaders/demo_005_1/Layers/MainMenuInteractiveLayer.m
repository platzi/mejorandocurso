//
//  MainMenuInteractiveLayer.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "MainMenuInteractiveLayer.h"


@implementation MainMenuInteractiveLayer

- (id)init
{
    self = [super init];
    if (self) {
        
//        kScreenMiddleWidthLocal = kScreenMiddleHeight;
//        kScreenMiddleHeightLocal = kScreenMiddleWidth;
//        kScreenWidthLocal = kScreenHeight;
//        kScreenHeightLocal = kScreenWidth;
//        
        self.isTouchEnabled = YES;
    }
    return self;
}

- (void)onEnter
{
    [super onEnter];
    [self loadGameLabels];
    [self loadMenuItems];
    [self loadBackgroundMusic];
    [self loadNyanCatAnimation];
}

- (void)loadGameLabels
{
    CCLabelBMFont *gameLabel1 = [CCLabelBMFont labelWithString:@"Nyan Cat"
                                                      fntFile:kDefaultBitmapFontForPrompts];
    CCLabelBMFont *gameLabel2 = [CCLabelBMFont labelWithString:@"vs"
                                                       fntFile:kDefaultBitmapFontForPrompts];
    CCLabelBMFont *gameLabel3 = [CCLabelBMFont labelWithString:@"Space Invaders"
                                                       fntFile:kDefaultBitmapFontForPrompts];
    
    gameLabel1.position = ccp(kScreenMiddleWidth, kScreenHeight - 25);
    gameLabel2.position = ccp(kScreenMiddleWidth, kScreenHeight - 55);
    gameLabel3.position = ccp(kScreenMiddleWidth, kScreenHeight - 85);
    
    [self addChild:gameLabel1
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 10];
    [self addChild:gameLabel2
                 z:kDefaultInteractiveLayerZValue + 2
               tag:kDefaultInteractiveLayerTag + 10];
    [self addChild:gameLabel3
                 z:kDefaultInteractiveLayerZValue + 3
               tag:kDefaultInteractiveLayerTag + 10];
    
}

- (void)loadMenuItems
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
                                                                                     scene:[NyanCatVsRobotAliens scene]]];
                                      }];
    
    CCMenuItemLabel *menuOptions = [CCMenuItemLabel
                                    itemWithLabel:optionsGameLabel
                                    block:^(id sender) {
                                        [[GameManager sharedGameManager] playSFX:kSfxTap];
                                    }];
    
    CCMenu *mainMenu = [CCMenu menuWithItems:menuStartGame, menuOptions, nil];
	[mainMenu alignItemsVertically];
    
    [mainMenu setScale:0.85f];
    
    mainMenu.position = ccp(kScreenMiddleWidth - 28.0f, kScreenMiddleHeight/8);
    
	// add the menu to your scene
	[self addChild:mainMenu
                 z:kDefaultInteractiveLayerZValue + 1
               tag:kDefaultInteractiveLayerTag + 20];
    
}

- (void)loadBackgroundMusic
{
    [[GameManager sharedGameManager] playBackgroundMusic:kBackgroundTrackIntro loop:YES];
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
    nyanCatAnim.position = ccp(kScreenMiddleWidth - 150.0f, kScreenMiddleHeight);
    id catAction = [CCRepeatForever actionWithAction:[CCAnimate actionWithAnimation:catAnim]];
    [nyanCatAnim runAction:catAction];
    [self addChild:nyanCatAnim];
    
    [self spawnRobotAliens];
    
}

-(void)spawnRobotAliens
{
    NSInteger kAlienBaseType = 101;
    for (int i = 0; i < 100; i++) {
        int alienType = (arc4random() % 4) + 1;
        CCSprite *alien;
        switch (alienType) {
            case 1:
                alien = [CCSprite spriteWithSpriteFrameName:@"invader1.png"];
                break;
            case 2:
                alien = [CCSprite spriteWithSpriteFrameName:@"invader2-1.png"];
                break;
            case 3:
                alien = [CCSprite spriteWithSpriteFrameName:@"invader3-1.png"];
                break;
            case 4:
                alien = [CCSprite spriteWithSpriteFrameName:@"invader4-1.png"];
                break;
        }
        alien.scale = 0.75f;
        
        NSInteger y = (arc4random() % (NSInteger)kScreenHeight - 10) + 10;
        NSInteger x = kScreenWidth + 200;
        
        NSInteger slowFactor = (arc4random() & 2) + 0.75;
        
        alien.position = ccp(x, y);
        id delay = [CCDelayTime actionWithDuration:0.5 + i/2];
        id moveUp = [CCMoveTo actionWithDuration:(2.75f + slowFactor)
                                        position:ccp(-200, y)];
        id resetPosition = [CCMoveTo actionWithDuration:0
                                               position:ccp(x, y)];
        id moveAliensSequence =[CCRepeatForever actionWithAction:[CCSequence actions:delay, moveUp, resetPosition, nil]];
        [self addChild:alien
                     z:kDefaultButtonZValue + 1
                   tag:(kAlienBaseType + i)];
        [alien runAction:moveAliensSequence];
    }
}

@end
