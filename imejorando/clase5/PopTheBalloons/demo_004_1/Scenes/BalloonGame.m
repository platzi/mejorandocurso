//
//  BalloonGame.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "BalloonGame.h"


@implementation BalloonGame

+(CCScene *)scene{
	CCScene *scene = [CCScene node];
    BalloonGameBackgroundLayer *backLayer = [BalloonGameBackgroundLayer node];
    BalloonGameInteractiveLayer *frontLayer = [BalloonGameInteractiveLayer node];
	[scene addChild:backLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultBackgroundLayerTag];
    [scene addChild:frontLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultInteractiveLayerTag];
	return scene;
}

@end
