//
//  Tweet.h
//  Demo1
//
//  Created by Raquel Hernandez on 10/14/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Tweet : NSObject
@property (strong, nonatomic) NSString *tweetBody;
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSDate *tweetedAt;
@property (nonatomic)BOOL retweeted;

- (NSInteger)numberoOfCharacters;

@end
