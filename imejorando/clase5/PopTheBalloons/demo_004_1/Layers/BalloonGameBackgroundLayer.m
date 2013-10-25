//
//  BalloonGameBackgroundLayer.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "BalloonGameBackgroundLayer.h"


@implementation BalloonGameBackgroundLayer

- (id)init
{
    self = [super init];
    if (self) {
        CCSprite *backgroundImage;
        if(IS_IPHONE_5())
            backgroundImage = [CCSprite spriteWithFile:@"popTheBalloonsBackground-568h@2x.png"];
        else
            backgroundImage = [CCSprite spriteWithFile:@"popTheBalloonsBackground.png"];
        [backgroundImage setPosition:CGPointMake(kScreenMiddleWidth, kScreenMiddleHeight)];
        [self addChild:backgroundImage z:0 tag:0];
    }
    return self;
}

@end
