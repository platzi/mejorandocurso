//
//  NyanCatInteractiveLayer.h
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "cocos2d.h"
#import "Constants.h"
#import "GameManager.h"
#import "SneakyJoystick.h"
#import "SneakyButton.h"
#import "SneakyButtonSkinnedBase.h"
#import "SneakyJoystickSkinnedBase.h"
#import "CCParticleSystemQuad.h"
#import "ModalAlert.h"
#import "MainMenu.h"

@interface NyanCatInteractiveLayer : CCLayer {
}

@property (nonatomic, strong) SneakyJoystick *joystick;
@property (nonatomic, strong) SneakyButton *fireButton;
@property (nonatomic, strong) CCSprite *nyanCat;
@property (nonatomic, assign) BOOL canFireLaser;
@property (nonatomic, assign) float lastShotFired;
@property (nonatomic, strong) NSMutableArray *lasers;
@property (nonatomic, strong) NSMutableArray *aliens;
@property (nonatomic, strong) NSMutableArray *powerups;
@property (nonatomic, assign) NSInteger nyans;
@property (nonatomic, assign) NSInteger deadAliens;
@property (nonatomic, assign) NSInteger catHealth;
@property (nonatomic, assign) CharacterStates catState;

@end
