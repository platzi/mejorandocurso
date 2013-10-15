//
//  ViewController.h
//  Demo1
//
//  Created by Raquel Hernandez on 10/14/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Tweet.h"

@interface ViewController : UIViewController<UITextFieldDelegate>
@property (weak, nonatomic) IBOutlet UITextField *viewTweetBody;
@property (weak, nonatomic) IBOutlet UILabel *label;
@property (strong, nonatomic) Tweet *tweet;

- (IBAction)tweetAction:(id)sender;

@end
