//
//  ContentView.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/14/20.
//

import SwiftUI
import CovidTrackingShared

struct StateListView: View {
    var fetcher = DailyDataFetcher()
    @State var data: [StateData] = []
    @State var dataLoaded = false
    var stateNames = StateNames()
    
    var body: some View {
        NavigationView {
            List {
                if (!dataLoaded) {
                    Text("Loading...")
                }
                else {
                    ForEach(data, id: \.state) { state in
                        NavigationLink(
                            destination: StateDataView(state: state, stateNames: stateNames),
                            label: {
                                HStack {
                                    Text(stateNames.getName(abbreviation: state.state))
                                    Spacer()
                                    Text("\(state.dailyData[0].positive ?? 0)")
                                }
                            })
                    }
                }
            }.navigationBarTitle("States")
        }.onAppear {
            fetcher.getDailyData { (data) in
                DispatchQueue.main.async {
                    self.data = data
                    self.dataLoaded = true
                }
            }
        }
    }
}

struct StateListView_Previews: PreviewProvider {
    static var previews: some View {
        StateListView()
    }
}
                                
