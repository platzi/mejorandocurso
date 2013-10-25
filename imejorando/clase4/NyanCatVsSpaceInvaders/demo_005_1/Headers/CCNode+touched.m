//
//  CCNode+touched.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/14/13.
//
//

#import "CCNode+touched.h"

@implementation CCNode (touched)

+(BOOL)touched:(NSSet *)touches node:(CCNode*)node
{
    UITouch * touch = [touches anyObject];
    CGPoint location = [[CCDirector sharedDirector] convertToUI:[touch locationInView:touch.view]];
    return (CGRectContainsPoint(node.boundingBox, location) ? YES : NO);
}

@end
