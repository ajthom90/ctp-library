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
    
    var body: some View {
        ScrollView {
            VStack {
                HStack {
                    Text("Postive Cases").fontWeight(.bold)
                    Spacer()
                    Text("\(daily.positive ?? 0)")
                }
                HStack {
                    Text("Number of New Cases").fontWeight(.bold)
                    Spacer()
                    Text("\(daily.positiveIncrease)")
                }
                HStack {
                    Text("Confirmed Deaths").fontWeight(.bold)
                    Spacer()
                    Text("\(daily.death ?? 0)")
                }
                HStack {
                    Text("Number of New Deaths").fontWeight(.bold)
                    Spacer()
                    Text("\(daily.deathIncrease)")
                }
                HStack {
                    Text("Recoveries").fontWeight(.bold)
                    Spacer()
                    Text("\(daily.recovered ?? 0)")
                }
                Spacer()
            }.padding([.trailing, .leading], 10)
        }.navigationBarTitle("\(daily.formattedDate())")
        .navigationBarItems(trailing: HStack {
            Button(action: {
                if let previousDay = self.daily.previousDay {
                    DispatchQueue.main.async {
                        self.daily = previousDay
                    }
                }
            }, label: {
                Image(systemName: "arrowtriangle.down.fill")
            }).disabled(self.daily.previousDay == nil)
            Spacer(minLength: 20)
            Button(action: {
                if let nextDay = self.daily.nextDay {
                    DispatchQueue.main.async {
                        self.daily = nextDay
                    }
                }
            }, label: {
                Image(systemName: "arrowtriangle.up.fill")
            }).disabled(self.daily.nextDay == nil)
        })
    }
}

struct StateDailyDataView_Previews: PreviewProvider {
    static var previews: some View {
        StateDailyDataView(state: StateData(state: "MN", dailyData: []), daily: StateDailyData(date: "20200714", state: "MN", positive: 2, negative: 4, pending: 4, hospitalizedCurrently: nil, hospitalizedCumulative: nil, inIcuCurrently: nil, inIcuCumulative: nil, onVentilatorCurrently: nil, onVentilatorCumulative: nil, recovered: nil, dataQualityGrade: nil, lastUpdateEt: nil, dateModified: nil, checkTimeEt: nil, death: nil, hospitalized: nil, dateChecked: nil, totalTestsViral: nil, positiveTestsViral: nil, negativeTestsViral: nil, positiveCasesViral: nil, deathConfirmed: nil, deathProbable: nil, fips: "27", positiveIncrease: 7, negativeIncrease: 4, total: 11, totalTestResults: 23, totalTestResultsIncrease: 32, posNeg: 107, deathIncrease: 42, hospitalizedIncrease: nil, hash: "hash", commercialScore: 12, negativeRegularScore: 12, negativeScore: 12, positiveScore: 2, score: 7, grade: "", previousDay: nil, nextDay: nil))
    }
}
