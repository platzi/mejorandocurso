//
//  GameManager.h
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//
//

#import <Foundation/Foundation.h>
#import "cocos2d.h"
#import "Constants.h"
#import "SimpleAudioEngine.h"

@interface GameManager : NSObject{
    
}

#pragma mark -
#pragma mark Properties

@property (nonatomic, assign) BOOL isMusicON;
@property (nonatomic, assign) BOOL isSoundEffectsON;
@property (nonatomic, assign) float backgroundMusicVolume;
@property (nonatomic, assign) float SFXVolume;

#pragma mark -
#pragma mark Initialization

+(GameManager*)sharedGameManager;

#pragma mark -
#pragma mark Sound and Music

-(void)configureSoundEngine;
-(void)preLoadSFX;
-(void)preLoadMusic;
-(ALuint)playSFX:(NSString*)effect;
-(ALuint)playLoopedSFX:(NSString*)effect;
-(void)stopSFX:(ALuint)effect;
-(void)stopAllSounds;
-(void)playBackgroundMusic:(NSString*)song loop:(BOOL)loop;
-(void)stopBackgroundMusic;


@end
