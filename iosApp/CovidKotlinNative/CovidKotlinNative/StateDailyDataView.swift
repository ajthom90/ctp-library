//
//  StateDailyDataView.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/14/20.
//

import SwiftUI
import CovidTrackingShared

struct StateDailyDataView: View {
    var state: StateData
    @State var daily: StateDailyData
    @Binding var selectedDaily: StateDailyData?
    
    var body: some View {
        ScrollView {
            VStack {
                StateDailyDataPointView("Number of Active Cases", number: daily.activeCases())
                StateDailyDataPointView("Positive Cases", number: daily.getPositive())
                StateDailyDataPointView("Number of New Cases", number: daily.positiveIncrease)
                StateDailyDataPointView("Confirmed Deaths", number: daily.getDeaths())
                StateDailyDataPointView("Number of New Deaths", number: daily.deathIncrease)
                StateDailyDataPointView("Recoveries", number: daily.getRecovered())
                StateDailyDataPointView("In Hospital Currently", number: daily.getHospitalizedCurrently())
                Spacer()
            }.padding(.all, 10)
        }.onDisappear {
            DispatchQueue.main.async {
                self.selectedDaily = nil
            }
        }.navBarTitle("\(daily.formattedDate())")
        .navBarItems(leading: EmptyView(), trailing: HStack {
            Button(action: {
                if let previousDay = self.daily.previousDay {
                    DispatchQueue.main.async {
                        self.daily = previousDay
                        self.selectedDaily = previousDay
                    }
                }
            }, label: {
                #if os(macOS)
                Text("Previous")
                #else
                Image(systemName: "arrowtriangle.down.fill")
                #endif
            }).disabled(self.daily.previousDay == nil)
            Spacer(minLength: 20)
            Button(action: {
                if let nextDay = self.daily.nextDay {
                    DispatchQueue.main.async {
                        self.daily = nextDay
                        self.selectedDaily = nextDay
                    }
                }
            }, label: {
                #if os(macOS)
                Text("Next")
                #else
                Image(systemName: "arrowtriangle.up.fill")
                #endif
            }).disabled(self.daily.nextDay == nil)
        })
    }
}

struct StateDailyDataView_Previews: PreviewProvider {
    static let daily = StateDailyData(date: "20200714", state: "MN", states: 7, positive: 2, negative: 4, pending: 4, hospitalizedCurrently: nil, hospitalizedCumulative: nil, inIcuCurrently: nil, inIcuCumulative: nil, onVentilatorCurrently: nil, onVentilatorCumulative: nil, recovered: nil, dataQualityGrade: nil, lastUpdateEt: nil, dateModified: nil, checkTimeEt: nil, death: nil, hospitalized: nil, dateChecked: nil, totalTestsViral: nil, positiveTestsViral: nil, negativeTestsViral: nil, positiveCasesViral: nil, deathConfirmed: nil, deathProbable: nil, fips: "27", positiveIncrease: 7, negativeIncrease: 4, total: 11, totalTestResults: 23, totalTestResultsIncrease: 32, posNeg: 107, deathIncrease: 42, hospitalizedIncrease: nil, hash: "hash", commercialScore: 12, negativeRegularScore: 12, negativeScore: 12, positiveScore: 2, score: 7, grade: "", previousDay: nil, nextDay: nil)
    static let stateData = StateData(state: "MN", dailyData: [daily])
    
    static var previews: some View {
        StateDailyDataView(state: StateData(state: "MN", dailyData: []), daily: self.daily, selectedDaily: .constant(daily))
    }
}
