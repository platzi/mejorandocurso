//
//  button.m
//  Classroom Demo
//
//  Created by Nick Pannuto on 2/10/10.
//  Copyright 2010 Sneakyness, llc.. All rights reserved.
//

#import "SneakyButton.h"

@implementation SneakyButton

@synthesize status, value, active, isHoldable, isToggleable, rateLimit, radius;

- (void) onEnterTransitionDidFinish
{
	[[CCDirector sharedDirector].touchDispatcher addTargetedDelegate:self priority:1 swallowsTouches:YES];
}

- (void) onExit
{
	[[CCDirector sharedDirector].touchDispatcher removeDelegate:self];
}

-(id)initWithRect:(CGRect)rect{
	self = [super init];
	if(self){
		
		bounds = CGRectMake(0, 0, rect.size.width, rect.size.height);
		center = CGPointMake(rect.size.width/2, rect.size.height/2);
		status = 1; //defaults to enabled
		active = NO;
		value = 0;
		isHoldable = 0;
		isToggleable = 0;
		radius = 32.0f;
		rateLimit = 1.0f/120.0f;
		
		position_ = rect.origin;
	}
	return self;
}

-(void)limiter:(float)delta{
	value = 0;
	[self unschedule: @selector(limiter:)];
	active = NO;
}

- (void) setRadius:(float)r
{
	radius = r;
	radiusSq = r*r;
}

#pragma mark Touch Delegate

- (BOOL)ccTouchBegan:(UITouch *)touch withEvent:(UIEvent *)event
{
	if (active) return NO;
	
	CGPoint location = [[CCDirector sharedDirector] convertToGL:[touch locationInView:[touch view]]];
	location = [self convertToNodeSpace:location];
		//Do a fast rect check before doing a circle hit check:
	if(location.x < -radius || location.x > radius || location.y < -radius || location.y > radius){
		return NO;
	}else{
		float dSq = location.x*location.x + location.y*location.y;
		if(radiusSq > dSq){
			active = YES;
			if (!isHoldable && !isToggleable){
				value = 1;
				[self schedule: @selector(limiter:) interval:rateLimit];
			}
			if (isHoldable) value = 1;
			if (isToggleable) value = !value;
			return YES;
		}
	}
return NO;
}

- (void)ccTouchMoved:(UITouch *)touch withEvent:(UIEvent *)event
{
	if (!active) return;
	
	CGPoint location = [[CCDirector sharedDirector] convertToGL:[touch locationInView:[touch view]]];
	location = [self convertToNodeSpace:location];
		//Do a fast rect check before doing a circle hit check:
	if(location.x < -radius || location.x > radius || location.y < -radius || location.y > radius){
		return;
	}else{
		float dSq = location.x*location.x + location.y*location.y;
		if(radiusSq > dSq){
			if (isHoldable) value = 1;
		}
		else {
			if (isHoldable) value = 0; active = NO;
		}
	}
}

- (void)ccTouchEnded:(UITouch *)touch withEvent:(UIEvent *)event
{
	if (!active) return;
	if (isHoldable) value = 0;
	if (isHoldable||isToggleable) active = NO;
}

- (void)ccTouchCancelled:(UITouch *)touch withEvent:(UIEvent *)event
{
	[self ccTouchEnded:touch withEvent:event];
}

@end
