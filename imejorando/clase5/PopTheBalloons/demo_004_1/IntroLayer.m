//
//  IntroLayer.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//  Copyright __MyCompanyName__ 2013. All rights reserved.
//


// Import the interfaces
#import "IntroLayer.h"
#import "MainMenu.h"


#pragma mark - IntroLayer

// HelloWorldLayer implementation
@implementation IntroLayer

// Helper class method that creates a Scene with the HelloWorldLayer as the only child.
+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	IntroLayer *layer = [IntroLayer node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

// 
-(void) onEnter
{
	[super onEnter];

    CCSprite *backgroundImage;
    if(IS_IPHONE_5())
        backgroundImage = [CCSprite spriteWithFile:@"balloonGameBackground-568h@2x.png"];
    else
        backgroundImage = [CCSprite spriteWithFile:@"baloonGameBackground.png"];
    [backgroundImage setPosition:CGPointMake(kScreenMiddleWidth, kScreenMiddleHeight)];
    
	// add the label as a child to this Layer
	[self addChild: backgroundImage];
	
	// In one second transition to the new scene
	[self scheduleOnce:@selector(makeTransition:) delay:1];
}

-(void) makeTransition:(ccTime)dt
{
	[[CCDirector sharedDirector] replaceScene:[CCTransitionFade
                                               transitionWithDuration:1.0
                                               scene:[MainMenu scene]]];
}
@end
