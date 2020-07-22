//
//  SearchBar.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/20/20.
//

import SwiftUI

struct SearchBar: View {
    @Binding var text: String
    
    @State private var isEditing = false
    
    var body: some View {
        HStack {
            TextField("Search...", text: $text).padding(7)
                .padding(.horizontal, 25)
                .background(searchBarBackgroundColor())
                .cornerRadius(8)
                .overlay(
                    HStack {
                        #if os(macOS)
                        EmptyView()
                        #else
                        Image(systemName: "magnifyingglass")
                            .foregroundColor(.gray)
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                            .padding(.leading, 8)
                        #endif
                                                
                        if isEditing {
                            Button(action: {
                                self.text = ""
                            }) {
                                #if os(macOS)
                                Text("x").bold()
                                #else
                                Image(systemName: "multiply.circle.fill")
                                    .foregroundColor(.gray)
                                    .padding(.trailing, 8)
                                #endif
                            }
                        }
                    }
                )
                .padding(.horizontal, 10)
                .transition(.move(edge: .trailing))
                .animation(.default)
                .onTapGesture {
                    isEditing = true
                }
            
            if isEditing {
                Button(action: {
                    self.isEditing = false
                    #if os(macOS)
                    NSApplication.shared.sendAction(#selector(NSResponder.resignFirstResponder), to: nil, from: nil)
                    #else
                    UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                    #endif
                }) {
                    Text("Cancel")
                }.padding(.trailing, 10)
                .transition(.move(edge: .trailing))
                .animation(.default)
            }
        }
    }
    
    func searchBarBackgroundColor() -> Color {
        #if os(macOS)
        return Color(NSColor.systemGray)
        #else
        return Color(UIColor.systemGray6)
        #endif
    }
}

struct SearchBar_Previews: PreviewProvider {
    static var previews: some View {
        SearchBar(text: .constant(""))
    }
}
