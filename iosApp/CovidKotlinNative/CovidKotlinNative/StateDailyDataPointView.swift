//
//  StateDailyDataPointView.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/21/20.
//

import SwiftUI

struct StateDailyDataPointView: View {
    var title: String
    var data: String
    
    init(_ title: String, number: Any?) {
        self.title = title
        self.data = "\(number ?? 0)"
    }
    
    init(_ title: String, data: String) {
        self.title = title
        self.data = data
    }
    
    var body: some View {
        HStack {
            Text(title).fontWeight(.bold)
            Spacer()
            Text(data)
        }
    }
}

struct StateDailyDataPointView_Previews: PreviewProvider {
    static var previews: some View {
        StateDailyDataPointView("Title", number: 17)
    }
}
