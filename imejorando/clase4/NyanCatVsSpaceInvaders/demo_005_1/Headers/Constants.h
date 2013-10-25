//
//  consts.h
//  demo_004_1
//
//  Created by Afrael Ortiz on 10/13/13.
//
//

#ifndef demo_004_1_consts_h
#define demo_004_1_consts_h

#pragma mark -
#pragma mark Screen Dimensions

#define kScreenSize [CCDirector sharedDirector].winSize

/*
#define kScreenMiddleWidth ([CCDirector sharedDirector].winSize.height /2)
#define kScreenMiddleHeight ([CCDirector sharedDirector].winSize.width /2)
#define kScreenWidth [CCDirector sharedDirector].winSize.height
#define kScreenHeight [CCDirector sharedDirector].winSize.width
*/

#define kScreenMiddleWidth ([CCDirector sharedDirector].winSize.width /2)
#define kScreenMiddleHeight ([CCDirector sharedDirector].winSize.height /2)
#define kScreenWidth [CCDirector sharedDirector].winSize.width
#define kScreenHeight [CCDirector sharedDirector].winSize.height


#pragma mark -
#pragma mark Defaults

#define kDefaultBackgroundLayerZValue 0
#define kDefaultInteractiveLayerZValue 10
#define kDefaultBackgroundLayerTag 100
#define kDefaultInteractiveLayerTag 200
#define kDefaultButtonZValue 10
#define kBalloonGameMaxBalloons 1000
#define kDefaultBackgroundMusicVolume 0.25f
#define kDefaultSFXVolume 0.25f
#define kDefaultBitmapFontForControls @"nyanCat_Silkscreen.fnt"
#define kDefaultBitmapFontForPrompts @"nyanCat_Silkscreen.fnt"
#define kDefaultGameClockInSeconds 20
#define kDefaultNyanCatTag 200

#pragma mark -
#pragma mark Device Detection

#define IS_RETINA_ENABLED() [[CCDirector sharedDirector] enableRetinaDisplay:YES]
#define IS_IPHONE_5() ([CCDirector sharedDirector].winSize.height == 568.0f)
#define IS_IPAD() UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad
#define IS_IPHONE() UI_USER_INTERFACE_IDIOM() != UIUserInterfaceIdiomPad

#pragma mark -
#pragma mark Background Music

#define kBackgroundTrack1 @"nyanCat1.mp3"
#define kBackgroundTrack2 @"nyanCat2.mp3"
#define kBackgroundTrack3 @"nyanCat3.mp3"
#define kBackgroundTrack4 @"nyanCat4.mp3"
#define kBackgroundTrackIntro @"nyanCatIntro.mp3"

#pragma mark -
#pragma mark Sound Effects

#define kSfxTap @"sfx_meow.mp3"
#define kSfxExplosion @"sfx_explosion.mp3"
#define kSfxLaser @"sfx_laser_shot.mp3"
#define kSfxBeep @"sfx_bleep.mp3"
#define kSfxCoin1 @"sfx_coin_1.mp3"
#define kSfxCoin2 @"sfx_coin_2.mp3"
#define kSfxExplosion2 @"sfx_explosion8bit.wav"
#define kSfxAngryCat @"sfx_angryCat.mp3"
#define kSfxHurtCat @"sfx_hurtCat.wav"
#define kSfxPowerup @"sfx_powerup.wav"

#pragma mark -
#pragma mark Enums

typedef enum {
    kStateIdle,
    kStateFlying,
    kStateTakingDamage,
    kStateDead
} CharacterStates;

typedef enum {
    kObjectTypeNone,
    kPowerUpType,
    kEnemyTypeAlienRobot,
    kNyanCatType,
    kCoinType
} GameObjectType;

#endif
