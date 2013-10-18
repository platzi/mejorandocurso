//
//  ViewController.h
//  MejorandolaApp
//
//  Created by Raquel Hernandez on 10/17/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MejorandoViewController : UIViewController<UICollectionViewDataSource, UICollectionViewDelegate>

@property (weak, nonatomic) IBOutlet UICollectionView *mejorandoCollectionView;
@property (strong, nonatomic) NSMutableArray *menuItems;

@end
