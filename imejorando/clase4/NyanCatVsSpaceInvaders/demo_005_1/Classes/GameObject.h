//
//  GameObject.h
//  demo_005_1
//
//  Created by Afrael Ortiz on 10/20/13.
//  Copyright 2013 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "cocos2d.h"
#import "Constants.h"


@interface GameObject : CCSprite {
    
}

@property (nonatomic, assign) BOOL isActive;
@property (nonatomic, assign) BOOL reactsToScreenBoundaries;
@property (readwrite) GameObjectType gameObjectType;
@property (readwrite) CharacterStates gameObjectState;

-(void)changeState:(CharacterStates)newState;
-(CGRect)adjustedBoundingBox;


@end
