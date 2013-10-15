//
//  ViewController.m
//  Whereami
//
//  Created by Raquel Hernandez on 10/13/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    // If we don't connect the MapView with the controller using the storyboard
    // we should uncomment the following line.
    // self.mapView.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)mapView:(MKMapView *)mapView didUpdateUserLocation:(MKUserLocation *)userLocation{
    // The following lines of code instructs the map view to zoom into a region that is 800 by 800 meters around the user’s location.
    MKCoordinateRegion region = MKCoordinateRegionMakeWithDistance(userLocation.coordinate, 800, 800);
    [self.mapView setRegion:[self.mapView regionThatFits:region] animated:YES];
    
    // Add an annotation
    MKPointAnnotation *point = [[MKPointAnnotation alloc] init];
    point.coordinate = userLocation.coordinate;
    point.title = @"Donde estoy?";
    point.subtitle = @"Estoy aqui!!!";
    
    [self.mapView addAnnotation:point];
    
}

@end


