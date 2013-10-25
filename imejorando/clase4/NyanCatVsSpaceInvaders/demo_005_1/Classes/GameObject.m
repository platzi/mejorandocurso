//
//  GameObject.m
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import "GameObject.h"


@implementation GameObject

-(id) init
{
    if((self=[super init])){
        self.isActive = TRUE;
        self.gameObjectType = kObjectTypeNone;
        [self changeState:kStateIdle];
    }
    return self;
}

-(void)changeState:(CharacterStates)newState
{
    CCLOG(@"Override");
}

-(CGRect)adjustedBoundingBox
{
    CCLOG(@"Override");
    return [self boundingBox];
}


@end
