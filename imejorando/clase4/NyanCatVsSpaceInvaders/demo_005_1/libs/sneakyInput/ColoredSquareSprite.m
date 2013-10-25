
#import "ColoredSquareSprite.h"

@interface ColoredSquareSprite (privateMethods)
- (void) updateContentSize;
- (void) updateColor;
@end


@implementation ColoredSquareSprite

@synthesize size=size_;
	// Opacity and RGB color protocol
@synthesize opacity=opacity_, color=color_;
@synthesize blendFunc=blendFunc_;

+ (id) squareWithColor: (ccColor4B)color size:(CGSize)sz
{
	return [[[self alloc] initWithColor:color size:sz] autorelease];
}

- (id) initWithColor:(ccColor4B)color size:(CGSize)sz
{
	if( (self=[self init]) ) {
		self.size = sz;
		
		color_.r = color.r;
		color_.g = color.g;
		color_.b = color.b;
		opacity_ = color.a;
	}
	return self;
}

- (void) dealloc
{
	free(squareVertices_);
	[super dealloc];
}

- (id) init
{
	if((self = [super init])){
		size_				= CGSizeMake(10.0f, 10.0f);
		
			// default blend function
		blendFunc_ = (ccBlendFunc) { CC_BLEND_SRC, CC_BLEND_DST };
		
		color_.r =
		color_.g =
		color_.b = 0U;
		opacity_ = 255U;
		
		squareVertices_ = malloc(sizeof(GLfloat)*2*(4));
		if(!squareVertices_){
			NSLog(@"Ack!! malloc in colored square failed");
			[self release];
			return nil;
		}
		memset(squareVertices_, 0, sizeof(GLfloat)*2*(4));
		
		self.size = size_;
	}
	return self;
}

- (void) setSize: (CGSize)sz
{
	size_ = sz;
	
	squareVertices_[0] = position_.x - size_.width;
	squareVertices_[1] = position_.y - size_.height;
	
	squareVertices_[2] = position_.x + size_.width;
	squareVertices_[3] = position_.y - size_.height;
	
	squareVertices_[4] = position_.x - size_.width;
	squareVertices_[5] = position_.y + size_.height;
	
	squareVertices_[6] = position_.x + size_.width;
	squareVertices_[7] = position_.y + size_.height;
	
	[self updateContentSize];
}

-(void) setContentSize: (CGSize)sz
{
	self.size = sz;
}

- (void) updateContentSize
{
	[super setContentSize:size_];
}

- (void)draw
{		
		// Default GL states: GL_TEXTURE_2D, GL_VERTEX_ARRAY, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY
		// Needed states: GL_VERTEX_ARRAY
		// Unneeded states: GL_COLOR_ARRAY, GL_TEXTURE_2D, GL_TEXTURE_COORD_ARRAY
	glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	glDisableClientState(GL_COLOR_ARRAY);
	glDisable(GL_TEXTURE_2D);
	
	glVertexPointer(2, GL_FLOAT, 0, squareVertices_);
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
	
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
	
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
	return (CGRectContainsPoint([self boundingBox], point));
}

- (NSString*) description
{
	return [NSString stringWithFormat:@"<%@ = %08X | Tag = %i | Color = %02X%02X%02X%02X | Size = %f,%f>", [self class], self, tag_, color_.r, color_.g, color_.b, opacity_, size_.width, size_.height];
}

@end