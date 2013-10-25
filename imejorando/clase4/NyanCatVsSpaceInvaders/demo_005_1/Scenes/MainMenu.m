//
//  MainMenu.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "MainMenu.h"


@implementation MainMenu

+(CCScene *)scene{
	CCScene *scene = [CCScene node];
    MainMenuBackgroundLayer *backLayer = [MainMenuBackgroundLayer node];
    MainMenuInteractiveLayer *frontLayer = [MainMenuInteractiveLayer node];
	[scene addChild:backLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultBackgroundLayerTag];
    [scene addChild:frontLayer
                  z:kDefaultBackgroundLayerZValue
                tag:kDefaultInteractiveLayerTag];
	return scene;
}

@end
