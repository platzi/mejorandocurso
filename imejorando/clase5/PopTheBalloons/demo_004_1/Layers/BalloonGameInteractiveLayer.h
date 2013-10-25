//
//  BalloonGameInteractiveLayer.h
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "cocos2d.h"
#import "Constants.h"
#import "CCNode+touched.h"
#import "GameManager.h"
#import "SimpleAudioEngine.h"
#import "CCParticleSystemQuad.h"
#import "ModalAlert.h"
#import "MainMenu.h"

#define kScoreLabelTag 101
#define kTimeLabelTag 102
#define kCloudBaseTag 90
#define kOffScreenPosition -1000

@interface BalloonGameInteractiveLayer : CCLayer {
    
}

@property (nonatomic, assign) NSInteger gameClock;
@property (nonatomic, assign) NSInteger score;

@end
