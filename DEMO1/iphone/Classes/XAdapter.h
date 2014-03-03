#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface XAdapter : NSObject{
	NSDictionary *parameter;
    NSObject *currentData;
    id parent;
    SEL selector;
}
-(UIView *)getView;
-(id)initWithDict:(NSDictionary *)dict  target:(id)target selector:(SEL)selector;
-(id)initWithDict:(NSDictionary *)dict  target:(id)target;
@property SEL selector;
@property (nonatomic, retain) id parent;
@property (nonatomic, retain) NSDictionary *parameter;
@property (nonatomic, retain) NSObject *currentData;
-(void)afterLoadDict:(NSDictionary *)dict;
-(void)afterLoadData:(NSString *)data;
-(NSObject *)getData:(NSArray *)command;
- (void)selectorPerformTargetAction: (NSDictionary *)action;
-(void)setData:(NSObject *)object command:(NSDictionary *)command;
-(void)applyCommand:(NSDictionary *)command target:(id)target;
-(void)freeData;
-(void)processAction:(NSDictionary *)action;
@end
