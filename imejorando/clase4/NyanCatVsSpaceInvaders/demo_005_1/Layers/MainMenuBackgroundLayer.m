//
//  MainMenuBackgroundLayer.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "MainMenuBackgroundLayer.h"


@implementation MainMenuBackgroundLayer

- (id)init
{
    self = [super init];
    if (self) {
    }
    return self;
}

- (void)onEnter
{
    [super onEnter];
    CCLayerColor* backgroundLayerColor = [CCLayerColor layerWithColor:ccc4(0, 51, 102, 255) width:kScreenWidth height:kScreenHeight];
    [self addChild:backgroundLayerColor];
    [self loadStarryField];
}

- (void)loadStarryField
{
    CCParticleSystemQuad *starField = [CCParticleSystemQuad particleWithFile:@"ccGlitterParticles.plist"];
    starField.position = ccp(kScreenMiddleWidth, kScreenMiddleHeight);
    [self addChild:starField
                 z:kDefaultInteractiveLayerZValue - 2
               tag:kDefaultInteractiveLayerTag];
    
}

@end
