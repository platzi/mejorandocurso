//
//  SneakyButtonSkinnedBase.m
//  SneakyInput
//
//  Created by Nick Pannuto on 2/19/10.
//  Copyright 2010 Sneakyness, llc.. All rights reserved.
//

#import "SneakyButtonSkinnedBase.h"
#import "SneakyButton.h"

@implementation SneakyButtonSkinnedBase

@synthesize defaultSprite, activatedSprite, disabledSprite, pressSprite, button;

- (void) dealloc
{
	[defaultSprite release];
	[activatedSprite release];
	[disabledSprite release];
	[pressSprite release];
	[button release];
	[super dealloc];
}

- (id) init
{
	self = [super init];
	if(self != nil){
		self.defaultSprite = nil;
		self.activatedSprite = nil;
		self.disabledSprite = nil;
		self.pressSprite = nil;
		self.button = nil;
		
		[self schedule:@selector(watchSelf)];
	}
	return self;
}

- (void) watchSelf
{
	if (!self.button.status){
		if(disabledSprite){
			disabledSprite.visible = YES;
		}
		else {
			disabledSprite.visible = NO;
		}
	}
	else {
		if(!self.button.active){
			pressSprite.visible = NO;
			if(self.button.value == 0){
				activatedSprite.visible = NO;
				if(defaultSprite){
					defaultSprite.visible = YES;
				}
			}
			else {
				activatedSprite.visible = YES;
			}
		}
		else {
			defaultSprite.visible = NO;
			if(pressSprite){
				pressSprite.visible = YES;
			}
		}
	}
}

- (void) setContentSize:(CGSize)s
{
	contentSize_ = s;
	defaultSprite.contentSize = s;
	button.radius = s.width/2;
}

- (void) setDefaultSprite:(CCSprite *)aSprite
{
	if(defaultSprite){
		if(defaultSprite.parent)
			[defaultSprite.parent removeChild:defaultSprite cleanup:YES];
		[defaultSprite release];
	}
	defaultSprite = [aSprite retain];
	if(aSprite){
		[self addChild:defaultSprite z:0];
		
		[self setContentSize:defaultSprite.contentSize];
	}
}

- (void) setActivatedSprite:(CCSprite *)aSprite
{
	if(activatedSprite){
		if(activatedSprite.parent)
			[activatedSprite.parent removeChild:activatedSprite cleanup:YES];
		[activatedSprite release];
	}
	activatedSprite = [aSprite retain];
	if(aSprite){
		[self addChild:activatedSprite z:1];
		
		[self setContentSize:activatedSprite.contentSize];
	}
}

- (void) setDisabledSprite:(CCSprite *)aSprite
{
	if(disabledSprite){
		if(disabledSprite.parent)
			[disabledSprite.parent removeChild:disabledSprite cleanup:YES];
		[disabledSprite release];
	}
	disabledSprite = [aSprite retain];
	if(aSprite){
		[self addChild:disabledSprite z:2];
		
		[self setContentSize:disabledSprite.contentSize];
	}
}

- (void) setPressSprite:(CCSprite *)aSprite
{
	if(pressSprite){
		if(pressSprite.parent)
			[pressSprite.parent removeChild:pressSprite cleanup:YES];
		[pressSprite release];
	}
	pressSprite = [aSprite retain];
	if(aSprite){
		[self addChild:pressSprite z:3];
		
		[self setContentSize:pressSprite.contentSize];
	}
}

- (void) setButton:(SneakyButton *)aButton
{
	if(button){
		if(button.parent)
			[button.parent removeChild:button cleanup:YES];
		[button release];
	}
	button = [aButton retain];
	if(aButton){
		[self addChild:button z:4];
		if(defaultSprite)
			[button setRadius:defaultSprite.contentSize.width/2];
	}
}

@end
