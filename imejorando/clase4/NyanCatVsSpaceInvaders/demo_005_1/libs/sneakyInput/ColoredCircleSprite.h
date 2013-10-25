#import "cocos2d.h"

@interface ColoredCircleSprite : CCNode <CCRGBAProtocol, CCBlendProtocol> {
	float		radius_;
	GLubyte		opacity_;
	ccColor3B	color_;
	
	NSUInteger numberOfSegments;
	GLfloat *circleVertices_;
	
	ccBlendFunc	blendFunc_;
}

@property (nonatomic,readwrite) float radius;

/** Opacity: conforms to CCRGBAProtocol protocol */
@property (nonatomic,readonly) GLubyte opacity;
/** Opacity: conforms to CCRGBAProtocol protocol */
@property (nonatomic,readonly) ccColor3B color;
/** BlendFunction. Conforms to CCBlendProtocol protocol */
@property (nonatomic,readwrite) ccBlendFunc blendFunc;

/** creates a Circle with color and radius */
+ (id) circleWithColor: (ccColor4B)color radius:(GLfloat)r;

/** initializes a Circle with color and radius */
- (id) initWithColor:(ccColor4B)color radius:(GLfloat)r;

- (BOOL) containsPoint:(CGPoint)point;

@end
