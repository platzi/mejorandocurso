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
#define kDefaultBitmapFontForControls @"PopTheBalloons_Pusab.fnt"
#define kDefaultBitmapFontForPrompts @"PopTheBalloons_SpicyRice.fnt"
#define kDefaultGameClockInSeconds 20

#pragma mark -
#pragma mark Device Detection

#define IS_RETINA_ENABLED() [[CCDirector sharedDirector] enableRetinaDisplay:YES]
#define IS_IPHONE_5() ([CCDirector sharedDirector].winSize.height == 568.0f)
#define IS_IPAD() UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad
#define IS_IPHONE() UI_USER_INTERFACE_IDIOM() != UIUserInterfaceIdiomPad

#pragma mark -
#pragma mark Background Music

#define kBackgroundTrack1 @"balloonGameBGMusic1.mp3"
#define kBackgroundTrack2 @"balloonGameBGMusic2.mp3"
#define kBackgroundTrack3 @"balloonGameBGMusic3.mp3"

#pragma mark -
#pragma mark Sound Effects

#define kSfxTap @"sfx_gummyClick.mp3"
#define kSfxBalloonPop @"sfx_balloon_pop.mp3"
#define kSfxBalloonDeflate @"sfx_balloon_deflating.mp3"
#define kSfxExplosion @"sfx_explosion.mp3"
#define kSfxClockTick @"sfx_bleepBoop.mp3"
#define kSfxCloudTap @"sfx_boingboing.mp3"
#define kSFXFail1 @"sfx_sadTrombone.mp3"
#define kSFXFail2 @"sfx_tpirFail.mp3"
#define kSFXWin1 @"sfx_hellYeah.mp3"
#define kSFXWin2 @"sfx_epicWin.mp3"

#endif
