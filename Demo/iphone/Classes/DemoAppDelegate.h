

#import <UIKit/UIKit.h>
#import "PROTOAppDelegate.h"

@interface DemoAppDelegate :NSObject <UIApplicationDelegate> {
    PROTOAppDelegate *dunamisDelegate;
}
@property (nonatomic, retain) PROTOAppDelegate *dunamisDelegate;

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

