//
//  AppDeleagte.h
//  PROTO
//
//  Created by BADA HONG on 12. 6. 7..
//  Copyright (c) 2012년 ASG Korea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FlashRuntimeExtensions.h"
#import "DunamisDelegate.h"
@interface DunamisAirDelegate : DunamisDelegate{
    FREContext eventContext;
}
@property  FREContext eventContext;
@end
