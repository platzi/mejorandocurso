//
//  CCNode+touched.h
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/14/13.
//
//

#import "CCNode.h"
#import "cocos2d.h"

@interface CCNode (touched)

+(BOOL)touched:(NSSet *)touches node:(CCNode*)node;

@end
