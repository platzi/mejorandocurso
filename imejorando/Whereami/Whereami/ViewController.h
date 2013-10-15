//
//  ViewController.h
//  Whereami
//
//  Created by Raquel Hernandez on 10/13/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

@interface ViewController : UIViewController<MKMapViewDelegate>
@property (weak, nonatomic) IBOutlet MKMapView *mapView;

@end




