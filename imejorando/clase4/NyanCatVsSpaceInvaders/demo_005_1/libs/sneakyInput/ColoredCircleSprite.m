
#import "ColoredCircleSprite.h"

@interface ColoredCircleSprite (privateMethods)
- (void) updateContentSize;
- (void) updateColor;
@end


@implementation ColoredCircleSprite

@synthesize radius=radius_;
	// Opacity and RGB color protocol
@synthesize opacity=opacity_, color=color_;
@synthesize blendFunc=blendFunc_;

+ (id) circleWithColor: (ccColor4B)color radius:(GLfloat)r
{
	return [[[self alloc] initWithColor:color radius:r] autorelease];
}

- (id) initWithColor:(ccColor4B)color radius:(GLfloat)r
{
	if( (self=[self init]) ) {
		self.radius	= r;
		
		color_.r = color.r;
		color_.g = color.g;
		color_.b = color.b;
		opacity_ = color.a;
	}
	return self;
}

- (void) dealloc
{
	free(circleVertices_);
	[super dealloc];
}

- (id) init
{
	if((self = [super init])){
		radius_				= 10.0f;
		numberOfSegments	= 36U;
		
			// default blend function
		blendFunc_ = (ccBlendFunc) { CC_BLEND_SRC, CC_BLEND_DST };
		
		color_.r =
		color_.g =
		color_.b = 0U;
		opacity_ = 255U;
		
		circleVertices_ = malloc(sizeof(GLfloat)*2*(numberOfSegments));
		if(!circleVertices_){
			NSLog(@"Ack!! malloc in colored circle failed");
			[self release];
			return nil;
		}
		memset(circleVertices_, 0, sizeof(GLfloat)*2*(numberOfSegments));
		
		self.radius			= radius_;
	}
	return self;
}

-(void) setRadius: (float) size
{
	radius_ = size;
	const float theta_inc	= 2.0f * 3.14159265359f/numberOfSegments;
	float theta				= 0.0f;
	
	for(int i=0; i<numberOfSegments; i++)
	{
		float j = radius_ * cosf(theta) + position_.x;
		float k = radius_ * sinf(theta) + position_.y;
		
		circleVertices_[i*2]	= j;
		circleVertices_[i*2+1]	= k;
		
		theta += theta_inc;
	}
	
	[self updateContentSize];
}

-(void) setContentSize: (CGSize) size
{
	self.radius	= size.width/2;
}

- (void) updateContentSize
{
	[super setContentSize:CGSizeMake(radius_*2, radius_*2)];
}

- (void)draw
{		
		// Default GL states: GL_TEXTURE_2D, GL_VERTEX_ARRAY, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY
		// Needed states: GL_VERTEX_ARRAY
		// Unneeded states: GL_COLOR_ARRAY, GL_TEXTURE_2D, GL_TEXTURE_COORD_ARRAY
	glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	glDisableClientState(GL_COLOR_ARRAY);
	glDisable(GL_TEXTURE_2D);
	
	glVertexPointer(2, GL_FLOAT, 0, circleVertices_);
	glColor4f(color_.r/255.0f, color_.g/255.0f, color_.b/255.0f, opacity_/255.0f);
	
	BOOL newBlend = NO;
	if( blendFunc_.src != CC_BLEND_SRC || blendFunc_.dst != CC_BLEND_DST ) {
		newBlend = YES;
		glBlendFunc(blendFunc_.src, blendFunc_.dst);
	}else if( opacity_ == 255 ) {
		newBlend = YES;
		glBlendFunc(GL_ONE, GL_ZERO);
	}else{
		newBlend = YES;
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	glDrawArrays(GL_TRIANGLE_FAN, 0, numberOfSegments);
	
	if( newBlend )
		glBlendFunc(CC_BLEND_SRC, CC_BLEND_DST);
	
		// restore default GL state
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	glEnableClientState(GL_COLOR_ARRAY);
	glEnable(GL_TEXTURE_2D);
}

#pragma mark Protocols
	// Color Protocol

-(void) setColor:(ccColor3B)color
{
	color_ = color;
}

-(void) setOpacity: (GLubyte) o
{
	opacity_ = o;
	[self updateColor];
}

#pragma mark Touch

- (BOOL) containsPoint:(CGPoint)point
{
	float dSq = point.x * point.x + point.y * point.y;
	float rSq = radius_ * radius_;
	return (dSq <= rSq );
}

- (NSString*) description
{
	return [NSString stringWithFormat:@"<%@ = %08X | Tag = %i | Color = %02X%02X%02X%02X | Radius = %1.2f>", [self class], self, tag_, color_.r, color_.g, color_.b, opacity_, radius_];
}

@end