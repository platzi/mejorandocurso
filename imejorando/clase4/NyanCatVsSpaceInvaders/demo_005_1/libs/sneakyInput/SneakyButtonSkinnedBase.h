//
//  SneakyButtonSkinnedBase.h
//  SneakyInput
//
//  Created by Nick Pannuto on 2/19/10.
//  Copyright 2010 Sneakyness, llc.. All rights reserved.
//

#import "cocos2d.h"

@class SneakyButton;

@interface SneakyButtonSkinnedBase : CCSprite {
	CCSprite *defaultSprite;
	CCSprite *activatedSprite;
	CCSprite *disabledSprite;
	CCSprite *pressSprite;
	SneakyButton *button;
}

@property (nonatomic, retain) CCSprite *defaultSprite;
@property (nonatomic, retain) CCSprite *activatedSprite;
@property (nonatomic, retain) CCSprite *disabledSprite;
@property (nonatomic, retain) CCSprite *pressSprite;

@property (nonatomic, retain) SneakyButton *button;

@end
