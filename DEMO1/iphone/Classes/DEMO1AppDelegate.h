

#import <UIKit/UIKit.h>
#import "PROTOAppDelegate.h"

@interface DEMO1AppDelegate :NSObject <UIApplicationDelegate> {
    PROTOAppDelegate *dunamisDelegate;
}
@property (nonatomic, retain) PROTOAppDelegate *dunamisDelegate;

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

