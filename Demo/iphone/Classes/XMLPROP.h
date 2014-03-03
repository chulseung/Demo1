//
//  XMLPROP.h
//  PROTO
//
//  Created by 바다 홍 on 10. 4. 5..
//  Copyright 2010 ASG Korea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TBXML.h"
#import <EventKit/EventKit.h>
#import <EventKitUI/EventKitUI.h>
#import <AddressBook/AddressBook.h>
#import <AddressBookUI/AddressBookUI.h>
//#import "ASIFormDataRequest.h"
@interface XMLPROP : NSObject  {
	NSData *xmlData;		
	NSDictionary *dataProvider;
	NSURLConnection *xmlFeedConnection;	
	NSDictionary *plist;
	TBXML *xmlDoc;
	BOOL done;
	id xmltarget;
	SEL xmlselector;	
	NSDictionary *parentAction;
	id parentSender;
}

+ (NSMutableDictionary *)parsePersonRecord:(ABRecordRef)abrecord;
- (void)parsePersonDetail:(NSDictionary *)params;
- (void)parseMonthlyEvent:(NSDictionary *)params;
- (void)parsePerson:(NSDictionary *)params;
- (void)parseContactList;
- (NSMutableDictionary *)parseEKRecurrenceRule:(EKRecurrenceRule *)recurrenceRule;
- (NSMutableDictionary *)parseEKCalendar:calendar;
- (void)parseCalendar;
- (NSMutableDictionary *)makeEventDict:(EKEvent *)event dateFormat:(NSString *)dateFormat;
- (void)parseEvent:(NSDictionary *)params;
- (void)getSystemInfo:(NSDictionary *)params;
- (NSDictionary *) makePList;
- (NSDictionary *) makePList:(TBXMLElement *)hash;
@property (nonatomic, retain) id parentSender;
@property (nonatomic, retain) NSDictionary *parentAction;
@property (nonatomic, retain) id xmltarget;
@property (nonatomic) SEL xmlselector;
@property (nonatomic, retain) NSDictionary *plist;
@property (nonatomic, retain) NSDictionary *dataProvider;
@property (nonatomic, retain) NSData *xmlData;
@property (nonatomic, retain) TBXML *xmlDoc;
@property (nonatomic, retain) NSURLConnection *xmlFeedConnection;
- (void) parseXmlDoc:(NSObject *)xml;
- (void) performTargetAction;
- (void)downloadAndParse:(NSString *)url params:(NSDictionary *)params;
- (void)downloadAndParse:(NSString *)url params:(NSDictionary *)params encoding:(NSString *)encoding;
- (void)handleError:(NSError *)error;
- (NSDictionary *) traceXmlDoc;
@end
