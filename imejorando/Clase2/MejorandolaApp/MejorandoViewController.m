//
//  ViewController.m
//  MejorandolaApp
//
//  Created by Raquel Hernandez on 10/17/13.
//  Copyright (c) 2013 Raquel Hernandez. All rights reserved.
//

#import "MejorandoViewController.h"

@interface MejorandoViewController ()

    @property (strong, nonatomic) NSURLSession *session;
    @property (strong, nonatomic) NSURLSessionConfiguration *sessionConfiguration;

@end

@implementation MejorandoViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    self.menuItems = [[NSMutableArray alloc] init];
    NSURL *url = [NSURL URLWithString:@"http://mejorandoios.herokuapp.com/api/courses/all"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    self.sessionConfiguration = [NSURLSessionConfiguration defaultSessionConfiguration];
    self.session = [NSURLSession sessionWithConfiguration:self.sessionConfiguration];
    
    
    NSURLSessionDataTask *task = [self.session dataTaskWithRequest:request completionHandler:^(NSData *data, NSURLResponse *response, NSError *error){
        
        NSHTTPURLResponse *urlResponse = (NSHTTPURLResponse *)response;
        //now i can check if the status code is 200
        if (urlResponse.statusCode == 200) {
            NSLog(@"TENEMOS DATOS DE LA API");
            [self handleResults:data];
        }
        else{
            //handle errors here
        }
        
    }];
    [task resume];

}

- (void)handleResults:(NSData *)data{
    NSError *jsonError;
    NSDictionary *response = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:&jsonError];
    
    if (response) {
        
        for (NSDictionary *dataDictionary in response[@"data"]) {
            [self.menuItems addObject:dataDictionary];
        }
        //NSLog(@"%@", self.menuItems);
        NSLog(@"Es tiempo de hacer reload en la tabla.");
        
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.mejorandoCollectionView reloadData];
        });
        
    }
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return [self.menuItems count];
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"Cell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *) [cell viewWithTag:10];

#warning No terminamos de implementar nuestra celda.
//TODO LIST
// Si self.menuItems tiene elementos, entonces hay que obtener la image_url del hash
// Como accedemos a datos de un hash?
// Tenemos que usar un NSDictionary
// Ahora que ya tenemos el hash en un cellDictionarly
// TEnemos que obtener la llave image_url, imageUrlString tendrá la url
// Después tendremos que descargar esa imágen y lo haremos con un NSURLRequest
// Y ese request se iniciará con un NSURLSessionDataTask
// Obtén los dátos asincronamente.
// Sí todo sale bien, setea el imageView.image
//No olvides iniciar la tarea!!!

    return cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath
{
    NSDictionary *menuItemDictionary = [self.menuItems objectAtIndex:indexPath.row];
#warning Cuándo dámos click a un item en el colleciton view no estamos haciendo nada.
//TODO LIST
    
    
}


@end
