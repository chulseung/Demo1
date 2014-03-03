#import "DunamisDelegate.h"
@implementation DunamisDelegate

- (id)init{
    [super init];
	printXmlLog=0;
    debug=10000;
	projectName=[[NSString stringWithString:@"DUNAMIS_IPHONE"] retain];
	disabled=-1000; 
    return self;
}



-(NSMutableDictionary * ) doCustomAction:(NSDictionary *)action bundle:(NSDictionary *)bundle navigator:(UINavigationController *)navigator{
	NSString *method=[action valueForKey:@"method"];
	//NSString *command=[action valueForKey:@"command"];
	NSDictionary *extra=[action objectForKey:@"extra"];
	NSLog(@"custom %@ %@",method,bundle);
	NSMutableDictionary *param=nil;
	NSMutableDictionary *ret=[NSMutableDictionary dictionary];
	if([method isEqualToString:@"addTextField"]){
		if(extra!=nil){
			param=(NSMutableDictionary *)[extra objectForKey:@"parameter"];
			UITextView *view=[param objectForKey:@"text1"];
			NSLog(@"PARAM %@ %d",view.text,0);
			if(view.text==nil||[view.text length]==0){
				view.text=@"1";
			}else{
				int a=[view.text intValue];
				a++;
				view.text=[NSString stringWithFormat:@"%d",a];			
			}
		}
	}else if([method isEqualToString:@"callAction"]){
		[self processActionOfName:@"accessibleFromNative" source:nil];
	}else if([method isEqualToString:@"responseAction"]){
		[ret setObject:@"CUSTOMSUCCESS" forKey:@"_state_"];
	}
	return ret;
}

- (void)loadXmlData:(NSString *)xmlData params:(NSDictionary *)params  dataProvider:(NSDictionary *)dataProvider{
	NSError *_error;
	NSURLResponse *response;
	NSData *dataReply;
	NSString *stringReply;
	NSLog(@"loadXmlData %@",params);
	/*
	 url=[NSString stringWithString:xmlData];
	 NSString *pDataStr=@"";
	 NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL: [NSURL URLWithString: url]];
	 NSString *postLength = [NSString stringWithFormat:@"%d",[pDataStr length]];
	 NSString* aURLString = [pDataStr stringByReplacingOccurrencesOfString:@"+"
	 withString:@"%2B"];
	 [request setHTTPMethod: @"POST"]; 
	 [request setHTTPBody: [aURLString dataUsingEncoding: NSUTF8StringEncoding]];
	 [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
	 [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
	 [request setValue:@"application/xml" forHTTPHeaderField:@"mediatype"];
	 [request setValue:@"utf-8" forHTTPHeaderField:@"encoding"];
	 dataReply = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&_error];
	 stringReply = [[NSString alloc] initWithData:dataReply encoding:NSUTF8StringEncoding];
	 XMLPROP *xmlProp=[dataProvider objectForKey:@"xmlProp"];
	 [xmlProp parseXmlDoc:stringReply];
	 [xmlProp performTargetAction];
	 */ 
	
	stringReply = [NSString stringWithFormat:@"<plist><dict><key>_state_</key><string>SUCCESS</string></dict></plist>"];
	XMLPROP *xmlProp=[dataProvider objectForKey:@"xmlProp"];
	[xmlProp parseXmlDoc:stringReply];
	//[xmlProp setPlist:ret];
	[xmlProp performTargetAction];
}

@end
