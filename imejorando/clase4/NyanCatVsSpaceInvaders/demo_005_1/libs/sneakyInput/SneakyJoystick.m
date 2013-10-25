//
//  joystick.m
//  SneakyJoystick
//
//  Created by Nick Pannuto.
//  2/15/09 verion 0.1
//  
//  WIKI: http://wiki.github.com/sneakyness/SneakyJoystick/
//  HTTP SRC: http://github.com/sneakyness/SneakyJoystick.git
//  GIT: git://github.com/sneakyness/SneakyJoystick.git
//  Email: SneakyJoystick@Sneakyness.com 
//  IRC: #cocos2d-iphone irc.freenode.net

#import "SneakyJoystick.h"

#define SJ_PI 3.14159265359f
#define SJ_PI_X_2 6.28318530718f
#define SJ_RAD2DEG 180.0f/SJ_PI
#define SJ_DEG2RAD SJ_PI/180.0f

@interface SneakyJoystick(hidden)
- (void)updateVelocity:(CGPoint)point;
- (void)setTouchRadius;
@end

@implementation SneakyJoystick

@synthesize
stickPosition,
degrees,
velocity,
autoCenter,
isDPad,
hasDeadzone,
numberOfDirections,
joystickRadius,
thumbRadius,
deadRadius;

- (void) dealloc
{
	[super dealloc];
}

-(id)initWithRect:(CGRect)rect
{
	self = [super init];
	if(self){
		stickPosition = CGPointZero;
		degrees = 0.0f;
		velocity = CGPointZero;
		autoCenter = YES;
		isDPad = NO;
		hasDeadzone = NO;
		numberOfDirections = 4;
		
		self.joystickRadius = rect.size.width/2;
		self.thumbRadius = 32.0f;
		self.deadRadius = 0.0f;
		
		//Cocos node stuff
		position_ = rect.origin;
}
	return self;
}

- (void) onEnterTransitionDidFinish
{
	[[CCDirector sharedDirector].touchDispatcher addTargetedDelegate:self priority:1 swallowsTouches:YES];
}

- (void) onExit
{
	[[CCDirector sharedDirector].touchDispatcher removeDelegate:self];
}

-(void)updateVelocity:(CGPoint)point
{
	// Calculate distance and angle from the center.
	float dx = point.x;
	float dy = point.y;
	float dSq = dx * dx + dy * dy;
	
	if(dSq <= deadRadiusSq){
		velocity = CGPointZero;
		degrees = 0.0f;
		stickPosition = point;
		return;
	}

	float angle = atan2f(dy, dx); // in radians
	if(angle < 0){
		angle		+= SJ_PI_X_2;
	}
	float cosAngle;
	float sinAngle;
	
	if(isDPad){
		float anglePerSector = 360.0f / numberOfDirections * SJ_DEG2RAD;
		angle = roundf(angle/anglePerSector) * anglePerSector;
	}
	
	cosAngle = cosf(angle);
	sinAngle = sinf(angle);
	
	// NOTE: Velocity goes from -1.0 to 1.0.
	if (dSq > joystickRadiusSq || isDPad) {
		dx = cosAngle * joystickRadius;
		dy = sinAngle * joystickRadius;
	}
	
	velocity = CGPointMake(dx/joystickRadius, dy/joystickRadius);
	degrees = angle * SJ_RAD2DEG;
	
	// Update the thumb's position
	stickPosition = ccp(dx, dy);
}

- (void) setIsDPad:(BOOL)b
{
	isDPad = b;
	if(isDPad){
		hasDeadzone = YES;
		self.deadRadius = 10.0f;
	}
}

- (void) setJoystickRadius:(float)r
{
	joystickRadius = r;
	joystickRadiusSq = r*r;
}

- (void) setThumbRadius:(float)r
{
	thumbRadius = r;
	thumbRadiusSq = r*r;
}

- (void) setDeadRadius:(float)r
{
	deadRadius = r;
	deadRadiusSq = r*r;
}

#pragma mark Touch Delegate

- (BOOL)ccTouchBegan:(UITouch *)touch withEvent:(UIEvent *)event
{
	CGPoint location = [[CCDirector sharedDirector] convertToGL:[touch locationInView:[touch view]]];
	//if([background containsPoint:[background convertToNodeSpace:location]]){
	location = [self convertToNodeSpace:location];
	//Do a fast rect check before doing a circle hit check:
	if(location.x < -joystickRadius || location.x > joystickRadius || location.y < -joystickRadius || location.y > joystickRadius){
		return NO;
	}else{
		float dSq = location.x*location.x + location.y*location.y;
		if(joystickRadiusSq > dSq){
			[self updateVelocity:location];
			return YES;
		}
	}
	return NO;
}

- (void)ccTouchMoved:(UITouch *)touch withEvent:(UIEvent *)event
{
	CGPoint location = [[CCDirector sharedDirector] convertToGL:[touch locationInView:[touch view]]];
	location = [self convertToNodeSpace:location];
	[self updateVelocity:location];
}

- (void)ccTouchEnded:(UITouch *)touch withEvent:(UIEvent *)event
{
	CGPoint location = CGPointZero;
	if(!autoCenter){
		CGPoint location = [[CCDirector sharedDirector] convertToGL:[touch locationInView:[touch view]]];
		location = [self convertToNodeSpace:location];
	}
	[self updateVelocity:location];
}

- (void)ccTouchCancelled:(UITouch *)touch withEvent:(UIEvent *)event
{
	[self ccTouchEnded:touch withEvent:event];
}

@end
