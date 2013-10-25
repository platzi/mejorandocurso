//
//  GameManager.m
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//
//

#import "GameManager.h"


@implementation GameManager
static GameManager* _sharedGameManager = nil;

#pragma mark -
#pragma mark Initialization

+(GameManager*)sharedGameManager
{
    @synchronized([GameManager class]){
        if(!_sharedGameManager)
            _sharedGameManager = [[self alloc]init];
        return _sharedGameManager;
    }
    return nil;
}

+(id)alloc
{
    @synchronized([GameManager class]){
        NSAssert(_sharedGameManager == nil, @"Attempted to allocate second instance singleton");
        _sharedGameManager = [super alloc];
        return _sharedGameManager;
    }
    return nil;
}

-(id)init
{
    self = [super init];
    if (self) {
        [self setGameDefaults];
    }
    return self;
}

-(void)setGameDefaults
{
    self.isMusicON = YES;
    self.isSoundEffectsON = YES;
    self.backgroundMusicVolume = kDefaultBackgroundMusicVolume;
    self.SFXVolume = kDefaultSFXVolume;
    [self preLoadMusic];
    [self preLoadSFX];
}

#pragma mark -
#pragma mark Sound and Music related

-(void)setBackgroundMusicVolume:(float)newVolume
{
    _backgroundMusicVolume = newVolume;
    [CDAudioManager sharedManager].backgroundMusic.volume = _backgroundMusicVolume;
    [SimpleAudioEngine sharedEngine].backgroundMusicVolume = _backgroundMusicVolume;
}

-(void)setSFXVolume:(float)newVolume
{
    _SFXVolume = newVolume;
    [SimpleAudioEngine sharedEngine].effectsVolume = _SFXVolume;
}

-(void)configureSoundEngine
{
    [CDSoundEngine setMixerSampleRate:CD_SAMPLE_RATE_MID];
    [[CDAudioManager sharedManager] setResignBehavior:kAMRBStopPlay autoHandle:YES];
    [[CDAudioManager sharedManager] setMode:kAMM_MediaPlayback];
    [CDAudioManager sharedManager].backgroundMusic.volume = self.backgroundMusicVolume;
    [SimpleAudioEngine sharedEngine].backgroundMusicVolume = self.backgroundMusicVolume;
    [SimpleAudioEngine sharedEngine].effectsVolume = self.SFXVolume;
}

-(void)preLoadSFX
{

    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxTap];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxExplosion];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxLaser];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxBeep];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxCoin1];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxCoin2];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxExplosion2];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxAngryCat];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxHurtCat];
    [[SimpleAudioEngine sharedEngine] preloadEffect:kSfxPowerup];
}

-(void)preLoadMusic
{
    [CDSoundEngine setMixerSampleRate:CD_SAMPLE_RATE_MID];
    [[CDAudioManager sharedManager] setResignBehavior:kAMRBStopPlay autoHandle:YES];
    [[CDAudioManager sharedManager] preloadBackgroundMusic:kBackgroundTrack1];
    [[CDAudioManager sharedManager] preloadBackgroundMusic:kBackgroundTrack2];
    [[CDAudioManager sharedManager] preloadBackgroundMusic:kBackgroundTrack3];
}

-(ALuint)playSFX:(NSString*)effect
{
    if(self.isSoundEffectsON)
        return [[SimpleAudioEngine sharedEngine] playEffect:effect];
    else
        return -1;
}

-(ALuint)playLoopedSFX:(NSString*)effect
{
    if(self.isSoundEffectsON){
        ALuint sfx = [[SimpleAudioEngine sharedEngine] playEffect:effect];
        alSourcei(sfx, AL_LOOPING, 1);
        return sfx;
    }
    else
        return -1;
}

-(void)stopSFX:(ALuint)effect
{
    [[SimpleAudioEngine sharedEngine] stopEffect:effect];
}

-(void)stopAllSounds
{
    [[SimpleAudioEngine sharedEngine] stopAllSounds];
}

-(void)playBackgroundMusic:(NSString*)song loop:(BOOL)loop
{
    if(self.isMusicON){
        [[CDAudioManager sharedManager] playBackgroundMusic:song loop:loop];
    }
}

-(void)stopBackgroundMusic
{
    [[CDAudioManager sharedManager] stopBackgroundMusic];
}

@end
