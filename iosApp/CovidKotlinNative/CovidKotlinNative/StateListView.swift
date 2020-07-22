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
    @State var usData: StateData? = nil
    
    @State var stateSearch: String = ""
    
    var body: some View {
        NavigationView {
            VStack {
                SearchBar(text: $stateSearch).padding(.top, 10)
                List {
                    if (!dataLoaded) {
                        Text("Loading...")
                    }
                    else {
                        if stateSearch.isEmpty && usData != nil {
                            NavigationLink(
                                destination: StateDataView(state: usData!, stateNames: self.stateNames),
                                label: {
                                    HStack {
                                        Text("United States")
                                        Spacer()
                                        Text("\(usData?.dailyData[0].getPositive() ?? 0)")
                                    }
                                })
                        }
                        ForEach(data.filter({ state in
                            if stateSearch.isEmpty {
                                return true
                            }
                            let name = stateNames.getName(abbreviation: state.state)
                            return name.lowercased().contains(stateSearch.lowercased())
                        }), id: \.state) { state in
                            NavigationLink(
                                destination: StateDataView(state: state, stateNames: stateNames),
                                label: {
                                    HStack {
                                        Text(stateNames.getName(abbreviation: state.state))
                                        Spacer()
                                        Text("\(state.dailyData[0].getPositive())")
                                    }
                                })
                        }
                    }
                }.listStyle(PlainListStyle())
            }.navBarTitleInline("States")
        }.onAppear {
            fetcher.getDailyData { (data) in
                DispatchQueue.main.async {
                    self.data = data
                    self.dataLoaded = true
                }
            }
            fetcher.getNationalDailyData { (data) in
                DispatchQueue.main.async {
                    self.usData = data
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
