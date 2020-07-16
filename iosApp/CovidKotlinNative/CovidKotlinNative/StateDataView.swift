//
//  StateDataView.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/14/20.
//

import SwiftUI
import CovidTrackingShared

struct StateDataView: View {
    var state: StateData
    var stateNames: StateNames
    
    var body: some View {
        List(state.dailyData, id: \.date) { daily in
            NavigationLink(destination: StateDailyDataView(state: state, daily: daily)) {
                HStack {
                    Text("\(daily.formattedDate())")
                    Spacer()
                    Text("\(daily.positive ?? 0) (+\(daily.positiveIncrease))")
                }
            }
        }.navigationBarTitle(stateNames.getName(abbreviation: state.state))
        .navigationTitle(stateNames.getName(abbreviation: state.state))
    }
}

struct StateDataView_Previews: PreviewProvider {
    static var previews: some View {
        StateDataView(state: StateData(state: "MN", dailyData: []), stateNames: StateNames())
    }
}
