#import <UIKit/UIKit.h>
#import "sqlite3.h"
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= 40000
#import <EventKit/EventKit.h>
#import <EventKitUI/EventKitUI.h>
#endif

#import "XMLPROP.h"
@interface XDictionary : NSObject{
}
-(NSMutableDictionary *)getDict;
@end

@interface XAirAdapter : NSObject{
}
-(void)dispatchDunamisEvent:(NSDictionary *)extra;
@end


@interface PROTOAppDelegate : NSObject <UINavigationControllerDelegate,UINavigationBarDelegate>{
	NSString *unlockCode;
    int scaleLocation;
    UIColor *defaultLabelColor;
    UIColor *defaultSeparatorColor;
    int defaultSeparatorStyle;
	NSString *projectName;
	NSMutableArray *freeObjects;
	NSMutableArray *freeXmlObjects;
	NSMutableDictionary *zippedXmls;
	NSMutableDictionary *gData;
	NSMutableDictionary *gHeader;
	NSMutableDictionary *loadedPlist;
	NSString *plistCaching;
	NSDictionary *plist;
	NSDictionary *userInfo;
	NSDictionary *gStyle;
	UIViewController *rootView;
	UIViewController *waitView;
	UITabBarController *tabCtrl;
	UINavigationController *rootNavigator;
	NSDictionary * map;
	NSDictionary * usedFiles;
	NSString *dataPath;
	NSString *backgroundImage;
	NSString *titleImage;
	NSString *plistUrl;
	NSError *error;
    
	sqlite3 *systemdb;
	sqlite3 *datadb;
	NSString *mapfile;
	NSString *globalstyle;
	NSString *platformVersion;
	NSString *updateUrl;
	NSString *debugServerPort;
	NSString *debugServer;
    NSString *firstActivityUrl;
    NSString *testWifi;
    NSString *test3g;
    NSString *targetDevice;
    NSString *deviceVersion;
    UIViewController  *splitView;
    BOOL showExplain;
	UIWindow *window;
	NSDictionary *oneChartData;
	int plistHotCache;
	int printXmlLog;
	int orientation;
	int disabled;
	int updateNum;
	int debug;
    
    NSMutableArray *actionInfo;
    NSMutableDictionary *plistInfo;
    
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= 40000
	EKEventStore *eventStore;
#endif
	int networkingCount;
	UITextField *activeField;
	BOOL licenced;
    BOOL saveParameterAtActionTime;
    BOOL applySubactionAtActionTime;
    BOOL debugDetailActionInfo;
    BOOL debugDunamisXml;
    
	UIActivityIndicatorView	*activityIndicator;
    float vWidth;
    float vHeight;
    float deviceWidth;
    float deviceHeight;
    BOOL isPortrait;
    NSObject* hostReach;
    NSObject* internetReach;
    NSObject* wifiReach;
    NSString *password;
    long lastActionTime;
    XAirAdapter *airAdapter;
    
}
@property BOOL saveParameterAtActionTime;
@property BOOL applySubactionAtActionTime;
@property BOOL debugDetailActionInfo;
@property BOOL debugDunamisXml;
@property BOOL isPortrait;
@property float vWidth;
@property float vHeight;
@property float deviceWidth;
@property float deviceHeight;
@property long lastActionTime;
@property int defaultSeparatorStyle;
@property int separatorStyle;
@property int scaleLocation;
@property int debug;
@property int plistHotCache;
@property int printXmlLog;
@property int networkingCount;
@property int orientation;
@property int disabled;
@property int updateNum;
@property (nonatomic, retain) UIActivityIndicatorView *activityIndicator;
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= 40000
@property (nonatomic, retain) EKEventStore *eventStore;
#endif
-(NSString *)userDbPath;
-(void)updateImageHolder:(NSString *)keyName pData:(NSData *)imgData;
-(void)updateAssets;
-(UIImage *)selectImageHolder:(NSString *)keyName;

@property (nonatomic, retain) NSMutableArray *actionInfo;
@property (nonatomic, retain) XAirAdapter *airAdapter;
@property (nonatomic, retain) NSMutableDictionary *plistInfo;
@property (nonatomic, retain) NSString *testWifi;
@property (nonatomic, retain) NSString *test3g;

@property (nonatomic, retain) UIColor *defaultLabelColor;
@property (nonatomic, retain) UIColor *defaultSeparatorColor;

@property (nonatomic, retain) NSString *targetDevice;
@property (nonatomic, retain) NSString *deviceVersion;
@property (nonatomic, retain) NSMutableDictionary *zippedXmls;
@property (nonatomic, retain) NSString  *debugServerPort;
@property (nonatomic, retain) NSString *password;
@property (nonatomic, retain) NSString  *debugServer;
@property (nonatomic, retain) UIViewController  *splitView;
@property (nonatomic, retain) NSString *firstActivityUrl;
@property (nonatomic, retain) NSMutableArray *freeObjects;
@property (nonatomic, retain) NSMutableArray *freeXmlObjects;
@property (nonatomic, retain) UITextField *activeField;
@property (nonatomic, retain) UIWindow *window;
@property (nonatomic, retain) NSString *updateUrl;
@property (nonatomic, retain) NSString *projectName;
@property (nonatomic, retain) NSString *platformVersion;
@property (nonatomic, retain) NSString *plistCaching;
@property (nonatomic, retain) UIViewController *rootView;
@property (nonatomic, retain) UIViewController *waitView;
@property (nonatomic, retain) NSDictionary *gStyle;
@property (nonatomic, retain) NSMutableDictionary *gData;
@property (nonatomic, retain) NSMutableDictionary *loadedPlist;
@property (nonatomic, retain) NSMutableDictionary *gHeader;
@property (nonatomic, retain) NSDictionary *userInfo;
@property (nonatomic, retain) NSDictionary *usedFiles;
@property (nonatomic, retain) NSDictionary * map;
@property (nonatomic, retain) NSString *dataPath;
@property (nonatomic, retain) NSString *backgroundImage;
@property (nonatomic, retain) NSString *titleImage;
@property (nonatomic, retain) NSString *plistUrl;
@property (nonatomic, retain) NSString *mapfile;
@property (nonatomic, retain) NSString *globalstyle;
@property (nonatomic, retain) NSString *unlockCode;
@property (nonatomic, retain) UITabBarController *tabCtrl;
@property (nonatomic, retain) UINavigationController *rootNavigator;
+ (PROTOAppDelegate *)sharedObject;
-(NSDictionary *)loadPlist:(NSString *)fname;
-(void )setAppDelegate:(id)delegate;
-(CGSize )SCALESize:(CGSize )size;
- (float) fontSize:(float) val;
-(CGPoint )SCALEPoint:(CGPoint )point;
-(CGPoint )scalePoint:(CGPoint )point;
-(CGSize )scaleSize:(CGSize )size;
- (void)addScaledView:(UIView *)parent view:(UIView *)view;
-(CGRect )SCALERect:(CGRect )rect;
-(CGRect )scaleRect:(CGRect )rect;
- (float) PSY:(float) val;
- (float) PSX:(float) val;
- (float) SY:(float) val;
- (float) SX:(float) val;
- (float) psy:(float) val;
- (float) psx:(float) val;
-(void)closeUserSystem;
-(void)openUserSystem;
- (float) sy:(float) val;
- (float) sx:(float) val;
- (void)ExecuteSql:(NSArray *)sqls;
- (NSString *)parseSkima:(BOOL)explain;
- (void)removeFile:(NSString *)path;
- (void)removeDir:(NSString *)path;
- (NSString *)parseDefintion:(NSDictionary *)skima prefix:(NSString *)prefix;
- (NSString *)parseSubtag:(NSDictionary *)skima prefix:(NSString *)prefix;
- (void)changeMasterDetail:(UIViewController *)masterView detailView:(UIViewController *)detailView;
- (void)removeXframe;
- (void)removeXtab;
- (void) onNavigatorLoadView:(UINavigationController *)navigator;
- (NSDictionary  *)getPlistData:(NSString *)query;
- (CGFloat)calcurateRowHeight:(NSDictionary *)dict indexPath:(NSIndexPath *)indexPath;
- (void)adjustListCell:(NSDictionary *)dict indexPath:(NSIndexPath *)indexPath cell:(UITableViewCell *)cell;
- (void)addXtab:(UIViewController *)controller;
- (NSObject *)parseJSON:(NSString *)obj;
- (void) setObjectValue:(NSString *)target value:(NSString *)value;
- (void)processXmlData:(NSObject *)xmlData dataProvider:(NSDictionary *)dataProvider;
- (void)loadXmlData:(NSString *)xmlData params:(NSDictionary *)params  dataProvider:(NSDictionary *)dataProvider;
- (NSDictionary *)hotCachedPlist:(NSString *)key;
- (void) hotCachePlist:(NSString *)key withObject:(NSObject *)withObject;
- (UIViewController *)makeNewRoot:(NSString *)Url;
- (void) checkProjectName:(NSDictionary *)data;
- (BOOL) checkUsedFile:(NSString *)url;
- (NSString *)testSwitch:(NSDictionary *)param;
- (NSDictionary *)makeFileData:(NSString *)Url unlockCode:(NSString *)unlockCode;
- (void) processLoadData:(NSDictionary *)data;
- (void)addXframe:(UIViewController *)controller;
- (NSString *)append:(NSString *)str with:(NSString *)with;
- (NSObject *)getTargetObject:(NSDictionary *)record path:(NSString *)path;
- (NSMutableDictionary *)makePlist:(NSString *)data;
- (NSDictionary *)getCustomComponent:(NSDictionary *)dict record:(NSDictionary *)record;
- (NSString *) makeParameter:(NSDictionary *)nsdict;
- (NSObject *)applyAllVariables:(NSDictionary *)source target:(NSObject *)target;
- (void) processAction:(NSDictionary *)action;
- (void) processAction:(id)form action:(NSDictionary *)action;
- (void) processActionOfName:(NSString *)actionName source:(NSDictionary *)source;
- (void) secureInputFinish:(NSInteger)type;
- (void) initEventStore;
- (void) didStartNetworking;
- (void) didStopNetworking;
- (void)loadAssets;
-(void)executeSqlFile:(NSString *)database;
- (void) addFreeObject:(NSObject *)target;
- (void) addFreeXml:(NSObject *)target;
- (void) setCustomViewData:(NSDictionary *)dict record:(NSDictionary *)record parent:(id)parent;
- (UIView *)getCustomView:(NSDictionary *)dict record:(NSDictionary *)record parent:(id)parent;
- (UIView *)getExtendedView:(NSDictionary *)dict record:(NSDictionary *)record parent:(id)parent;
- (UIViewController *) makeXForm:(NSDictionary *)plistData;
- (BOOL)testCondition:(NSDictionary *)param;
- (void)updateDataHolder:(NSString *)keyName pData:(NSString *)pData;
- (NSString *)filterString:(NSString *)type value:(NSString *)value record:(NSDictionary *)record;
- (NSString *)selectDataHolder:(NSString *)keyName;
- (void)makeHome:(NSString *)Url;
- (void)ExecuteASql:(NSString *)sqls;
- (NSArray  *)getArrayData:(NSString *)query;
- (NSString *)downloadAndSave:(NSString *)url;
- (void)startDownload:(NSObject *)targetObject dict:(NSDictionary *)dict;
- (NSString *)getColumn:(sqlite3_stmt *)stmt index:(int)index;
- (NSDictionary *)testSystemInfo;
- (NSString *)dbPath;
- (void)createSystem;
- (void)openSystem;
- (void)closeSystem;
- (BOOL)updateSystemInfo:(NSString *)sysVersion platform:(NSString *)platform installDt:(NSString *)installDt dict:(NSDictionary *)dict;
- (BOOL)updateUserInfo:(NSDictionary *)data;
- (NSObject *)downloadCustomData:(NSString *)url params:(NSDictionary *)params xmlProp:(XMLPROP *)xmlProp;
- (BOOL)evaluateFunction:(NSDictionary *)dict;
- (NSString *)getString:(NSDictionary *)dict target:(NSString *)name;
- (UIViewController *) makeController:(NSDictionary *)dict extra:(NSDictionary *)extra;
- (id)setCustomProp:(id)obj prop:(NSDictionary *)dict;
- (NSDictionary *)getCustomData:(NSDictionary *)dataProvider;
- (NSMutableDictionary * ) doExtendedAction:(NSDictionary *)action bundle:(NSDictionary *)bundle navigator:(UINavigationController *)navigator;
- (NSMutableDictionary * ) doCustomAction:(NSDictionary *)action bundle:(NSDictionary *)bundle navigator:(UINavigationController *)navigator;
- (void) applyObjectData:(NSDictionary *)action object:(NSObject *)object;
- (NSDictionary *) getXmlData:(NSDictionary *)dataProvider;
- (void) initCache;
- (void) clearCache;
- (NSString *)cachedPath:(NSString *)url;
- (UIViewController *)makeRoot:(NSString *)plistUrl;
- (UIViewController *)makeRoot2:(NSString *)Url detail:(NSString *)detail;
- (void)makeRootFrame:(NSString *)Url;
- (void)gotoUpdate:(NSString *)url;
- (void)disableTouchEvent:(NSDictionary *)dict;
- (void)enableTouchEvent:(NSDictionary *)dict;
-(void)dispatchDunamisEvent:(NSDictionary *)extra;
@end
