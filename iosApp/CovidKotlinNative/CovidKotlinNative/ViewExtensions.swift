//
//  ViewExtensions.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/22/20.
//

import Foundation
import SwiftUI

#if os(macOS)
extension View {
    func navBarTitle(_ title: String) -> some View {
        return self
    }
    
    func navTitle(_ title: String) -> some View {
        return self
    }
    
    func navBarTitleInline(_ title: String) -> some View {
        return self
    }
    
    func navBarItems<L, T>(leading: L, trailing: T) -> some View where L : View, T : View {
        return self
    }
}
#else
extension View {
    func navBarTitle(_ title: String) -> some View {
        return self.navigationBarTitle(title)
    }
    
    func navTitle(_ title: String) -> some View {
        return self
    }
    
    func navBarTitleInline(_ title: String) -> some View {
        return self.navigationBarTitle(Text(title), displayMode: .inline)
    }
    
    func navBarItems<L, T>(leading: L, trailing: T) -> some View where L : View, T : View {
        return self.navigationBarItems(leading: leading, trailing: trailing)
    }
}
#endif
