//
//  NyanCatBackgroundLayer.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "NyanCatBackgroundLayer.h"


@implementation NyanCatBackgroundLayer

- (id)init
{
    self = [super initWithColor:ccc4(0, 51, 102, 255) width:kScreenWidth height:kScreenHeight];
    if (self) {
        [self loadStarryField];
    }
    return self;
}

- (void)loadStarryField
{
    CCParticleSystemQuad *starField = [CCParticleSystemQuad particleWithFile:@"ccGlitterParticles.plist"];
    starField.position = ccp(kScreenMiddleWidth, kScreenMiddleHeight);
    [self addChild:starField
                 z:kDefaultInteractiveLayerZValue - 2
               tag:kDefaultInteractiveLayerTag];
    
    CCParticleSystemQuad *moreStars = [CCParticleSystemQuad particleWithFile:@"ccStarryFieldParticles.plist"];
    moreStars.position = ccp(kScreenWidth + 100, kScreenMiddleHeight);
    [self addChild:moreStars
                 z:kDefaultInteractiveLayerZValue - 1
               tag:kDefaultInteractiveLayerTag + 1];
    
    CCParticleSystemQuad *evenMoreStars = [CCParticleSystemQuad particleWithFile:@"ccStarry2FieldParticles.plist"];
    evenMoreStars.position = ccp(kScreenWidth + 100, kScreenMiddleHeight);
    [self addChild:evenMoreStars
                 z:kDefaultInteractiveLayerZValue - 1
               tag:kDefaultInteractiveLayerTag + 2];
    
}


@end
