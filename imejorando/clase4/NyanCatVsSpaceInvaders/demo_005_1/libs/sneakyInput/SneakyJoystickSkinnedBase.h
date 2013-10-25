//
//  SneakyJoystickSkinnedBase.h
//  SneakyJoystick
//
//  Created by CJ Hanson on 2/18/10.
//  Copyright 2010 Hanson Interactive. All rights reserved.
//

#import "cocos2d.h"

@class SneakyJoystick;

@interface SneakyJoystickSkinnedBase : CCSprite {
	CCSprite *backgroundSprite;
	CCSprite *thumbSprite;
	SneakyJoystick *joystick;
}

@property (nonatomic, retain) CCSprite *backgroundSprite;
@property (nonatomic, retain) CCSprite *thumbSprite;
@property (nonatomic, retain) SneakyJoystick *joystick;

- (void) updatePositions;

@end
