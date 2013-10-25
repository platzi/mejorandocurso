//
//  NyanCatVsRobotAliens.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "NyanCatVsRobotAliens.h"


@implementation NyanCatVsRobotAliens

+(CCScene *)scene
{
	CCScene *scene = [CCScene node];
    NyanCatBackgroundLayer *backLayer = [NyanCatBackgroundLayer node];
    NyanCatInteractiveLayer *frontLayer = [NyanCatInteractiveLayer node];
	[scene addChild:backLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultBackgroundLayerTag];
    [scene addChild:frontLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultInteractiveLayerTag];
	return scene;
}

@end
