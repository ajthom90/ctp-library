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
    @State var selectedDaily: StateDailyData? = nil
    
    var body: some View {
        List(selection: $selectedDaily) {
            #if os(iOS)
            NavigationLink(destination: StateChartsView(state: state)) {
                Text("Charts")
            }
            #endif
            ForEach(state.dailyData, id: \.date) { daily in
                NavigationLink(destination: StateDailyDataView(state: state, daily: daily, selectedDaily: $selectedDaily)) {
                    HStack {
                        Text("\(daily.formattedDate())")
                        Spacer()
                        if daily.positiveIncrease >= 0 {
                            Text("\(daily.getPositive()) (+\(daily.positiveIncrease))")
                        } else {
                            Text("\(daily.getPositive()) (\(daily.positiveIncrease))")
                        }
                    }
                }
            }
        }.onAppear {
            self.selectedDaily = state.dailyData[0]
        }.navBarTitle(stateNames.getName(abbreviation: state.state))
        .navTitle(stateNames.getName(abbreviation: state.state))
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

