#import "cocos2d.h"

@interface ColoredSquareSprite : CCNode <CCRGBAProtocol, CCBlendProtocol> {
	CGSize		size_;
	GLubyte		opacity_;
	ccColor3B	color_;
	
	GLfloat		*squareVertices_;
	
	ccBlendFunc	blendFunc_;
}

@property (nonatomic,readwrite) CGSize size;

/** Opacity: conforms to CCRGBAProtocol protocol */
@property (nonatomic,readonly) GLubyte opacity;

/** Opacity: conforms to CCRGBAProtocol protocol */
@property (nonatomic,readonly) ccColor3B color;

/** BlendFunction. Conforms to CCBlendProtocol protocol */
@property (nonatomic,readwrite) ccBlendFunc blendFunc;

/** creates a Square with color and size */
+ (id) squareWithColor: (ccColor4B)color size:(CGSize)sz;

/** initializes a Circle with color and radius */
- (id) initWithColor:(ccColor4B)color size:(CGSize)sz;

- (BOOL) containsPoint:(CGPoint)point;

@end
