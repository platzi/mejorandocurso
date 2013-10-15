//
//  ViewController.m
//  Demo1
//
//  Created by Raquel Hernandez on 10/14/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import "ViewController.h"
#import "Tweet.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    self.tweet = [[Tweet alloc] init];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)tweetAction:(id)sender {
    self.tweet.tweetBody = self.viewTweetBody.text;
    //You get this warning if you compile on OS X (64-bit), because on that platform NSInteger is defined as long and is a 64-bit integer.
    //You need to cast self.tweet.numberOfCharacters and specify its type as long
    //In Objective C, type casting is about using a pointer of the sub type to point to an object of its parent type.
    NSString *result = [[NSString alloc] initWithFormat:@"Enviaste %li caracteres", (long) self.tweet.numberoOfCharacters];
    self.label.text = result;
    //hide keyword
    [self.viewTweetBody resignFirstResponder];
    
}

//hide keyword when user press done on the keyword
- (BOOL)textFieldShouldReturn:(UITextField *)theTextField {
    [theTextField resignFirstResponder];
    return YES;
}


@end


