#import "DunamisAirDelegate.h"
#import "FlashRuntimeExtensions.h"
@implementation DunamisAirDelegate
@synthesize eventContext;


UIViewController *airView;
FREObject aneTrace(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    NSString * message;
    const uint8_t *argv0;
    uint32_t argv0Len;
    FREGetObjectAsUTF8(argv[0], &argv0Len, &argv0);
    message = [NSString stringWithUTF8String:(char*)argv0];
    NSLog(@"%@",message);
    return nil;
}

FREObject isSupported(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    FREObject retVal;
    DunamisAirDelegate *dunamisDelegate=(DunamisAirDelegate *)[DunamisAirDelegate sharedObject];
    dunamisDelegate.eventContext=ctx;
    if(FRENewObjectFromBool(YES, &retVal) == FRE_OK){
        return retVal;
    }else{
        return nil;
    }
}



FREObject showDunamis(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    DunamisAirDelegate *dunamisDelegate=(DunamisAirDelegate *)[DunamisAirDelegate sharedObject];
    if(dunamisDelegate.rootNavigator==nil){
        dunamisDelegate.eventContext=ctx;
        NSString * firstUrl;
        const uint8_t *argv0;
        uint32_t argv0Len;
        FREGetObjectAsUTF8(argv[0], &argv0Len, &argv0);
        firstUrl = [NSString stringWithUTF8String:(char*)argv0];
        NSLog(@"PASSED URL:%@",firstUrl);
        airView=[[[UIApplication sharedApplication] keyWindow] rootViewController];
        UIViewController *controller=[dunamisDelegate makeRoot:firstUrl];
        dunamisDelegate.rootNavigator=(UINavigationController *)controller;
        [[[UIApplication sharedApplication] keyWindow] setRootViewController:controller];
    }else{
        dunamisDelegate.eventContext=ctx;
        NSString * firstUrl;
        const uint8_t *argv0;
        uint32_t argv0Len;
        FREGetObjectAsUTF8(argv[0], &argv0Len, &argv0);
        firstUrl = [NSString stringWithUTF8String:(char*)argv0];
        NSLog(@"PASSED URL:%@",firstUrl);
        airView=[[[UIApplication sharedApplication] keyWindow] rootViewController];
        UIViewController *controller=dunamisDelegate.rootNavigator;
        [[[UIApplication sharedApplication] keyWindow] setRootViewController:controller];
    }
    //airView.modalPresentationStyle = UIModalPresentationCurrentContext;
    //[airView presentViewController:controller animated:YES completion:nil];
    //[controller release];
    return nil;
}

FREObject showDunamisModal(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    DunamisAirDelegate *dunamisDelegate=(DunamisAirDelegate *)[DunamisAirDelegate sharedObject];
    dunamisDelegate.eventContext=ctx;
    NSString * firstUrl;
    const uint8_t *argv0;
    uint32_t argv0Len;
    FREGetObjectAsUTF8(argv[0], &argv0Len, &argv0);
    firstUrl = [NSString stringWithUTF8String:(char*)argv0];
    NSLog(@"PASSED URL:%@",firstUrl);
    airView=[[[UIApplication sharedApplication] keyWindow] rootViewController];
	UIViewController *controller=[dunamisDelegate makeRoot:firstUrl];
    //airView.modalPresentationStyle = UIModalPresentationCurrentContext;
    [airView presentViewController:controller animated:YES completion:nil];
    [controller release];
    return nil;
}


FREObject dismissModalAir(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    [airView dismissViewControllerAnimated:YES completion:nil];
    return nil;
}


FREObject closeAir(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[] ){
    UINavigationController *ctrl=(UINavigationController *)[[[UIApplication sharedApplication] keyWindow] rootViewController];
    [ctrl popViewControllerAnimated:YES];
    return nil;
}


-(void) openAir:(NSDictionary *)action{
    UINavigationController *ctrl=(UINavigationController *)[[[UIApplication sharedApplication] keyWindow] rootViewController];
    //   ctrl.modalPresentationStyle = UIModalPresentationCurrentContext;
    [ctrl presentViewController:airView animated:YES completion:nil];
}


-(void) dispatchDunamisEvent:(NSDictionary *)action
{
    NSLog(@"DISPATCH:%@ ",action);
    NSDictionary *extra=[action objectForKey:@"extra"];
    NSString *value=nil;
    NSString *code=nil;
    NSString *method=nil;
    if(extra!=nil){
        NSDictionary *parameter=[extra objectForKey:@"parameter"];
        if(parameter!=nil){
            value=[parameter objectForKey:@"value"];
            code=[parameter objectForKey:@"eventcode"];
            method=[parameter objectForKey:@"method"];
        }
    }
    if([method isEqualToString:@"callBack"]){
        NSLog(@"SEND:%@ %@",code,value);
        const uint8_t* ccode = (const uint8_t*) [code UTF8String];
        const uint8_t* cvalue = (const uint8_t*) [value UTF8String];
        if (eventContext == NULL)
        {
            return;
        }
        FREDispatchStatusEventAsync(eventContext, ccode, cvalue);
    }else if([method isEqualToString:@"directCall"]){
        NSLog(@"directcall:%@ ",value);
        const uint8_t* ccode = (const uint8_t*) [code UTF8String];
        const uint8_t* cvalue = (const uint8_t*) [value UTF8String];
        if([value isEqualToString:@"openModalAir"]){
            UINavigationController *ctrl=(UINavigationController *)[[[UIApplication sharedApplication] keyWindow] rootViewController];
            [ctrl presentViewController:airView animated:YES completion:nil];
        }
        else if([value isEqualToString:@"openAir"]){
            UINavigationController *ctrl=(UINavigationController *)[[[UIApplication sharedApplication] keyWindow] rootViewController];
            [ctrl pushViewController:airView animated:YES];
            [self openAir:action];
        }else if([value isEqualToString:@"showAir"]){
            [[[UIApplication sharedApplication] keyWindow] setRootViewController:airView];
        }
        FREDispatchStatusEventAsync(eventContext, ccode, cvalue);
    }
}



void ContextInitializer(void* extData, const uint8_t * ctxType, FREContext ctx,
                        uint32_t* numFunctionsToTest, const FRENamedFunction** functionsToSet)
{
    int count=6;
    *numFunctionsToTest = count;
    FRENamedFunction* func = (FRENamedFunction*) malloc(sizeof(FRENamedFunction) * count);
    func[0].name = (const uint8_t *) "isSupported";
    func[0].functionData = NULL;
    func[0].function = &isSupported;
    
    func[1].name = (const uint8_t *) "showDunamis";
    func[1].functionData = NULL;
    func[1].function = &showDunamis;
    
    func[2].name = (const uint8_t *) "showDunamisModal";
    func[2].functionData = NULL;
    func[2].function = &showDunamisModal;
    
    func[3].name = (const uint8_t *) "dismissModalAir";
    func[3].functionData = NULL;
    func[3].function = &dismissModalAir;
    
    func[4].name = (const uint8_t *) "closeAir";
    func[4].functionData = NULL;
    func[4].function = &closeAir;
    
    func[5].name = (const uint8_t *) "aneTrace";
    func[5].functionData = NULL;
    func[5].function = &aneTrace;
    
    
    *functionsToSet = func;
}

void ContextFinalizer(FREContext ctx) {
	return;
}

void ExtInitializer(void** extDataToSet, FREContextInitializer* ctxInitializerToSet,
                    FREContextFinalizer* ctxFinalizerToSet) {
    *extDataToSet = NULL;
    *ctxInitializerToSet = &ContextInitializer;
    *ctxFinalizerToSet = &ContextFinalizer;
    
    DunamisAirDelegate *dunamisDelegate=(DunamisAirDelegate *)[DunamisAirDelegate sharedObject];
    dunamisDelegate.projectName=@"DUNAMIS_IPHONE";
    dunamisDelegate.debug=10000;
    dunamisDelegate.scaleLocation=0;
    dunamisDelegate.orientation=2;
}

void ExtFinalizer(void* extData) {
    return;
}


@end
