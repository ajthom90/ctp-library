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
        List {
            NavigationLink(destination: StateChartsView(state: state)) {
                Text("Charts")
            }
            ForEach(state.dailyData, id: \.date) { daily in
                NavigationLink(destination: StateDailyDataView(state: state, daily: daily)) {
                    HStack {
                        Text("\(daily.formattedDate())")
                        Spacer()
                        Text("\(daily.positive ?? 0) (\(daily.positiveIncreaseString()))")
                    }
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

extension StateDailyData {
    func positiveIncreaseString() -> String {
        if self.positiveIncrease >= 0 {
            return "+\(positiveIncrease)"
        }
        return "\(positiveIncrease)"
    }
}
