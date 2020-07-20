//
//  StateDailyCasesChart.swift
//  CovidKotlinNative
//
//  Created by ajthom90 on 7/16/20.
//

import SwiftUI
import CovidTrackingShared
import SwiftUICharts

struct StateChartsView: View {
    @State var state: StateData
    
    var body: some View {
        ScrollView {
            VStack {
                BarChartView(data: chartData(), title: "New Cases", form: ChartForm.extraLarge, dropShadow: false, valueSpecifier: "%.0f").padding(.bottom, 10)
                BarChartView(data: deathsChartData(), title: "New Deaths", form: ChartForm.extraLarge, dropShadow: false, valueSpecifier: "%.0f")
            }
        }.navigationBarTitle("Charts")
    }
    
    func chartData() -> ChartData {
        let values: [(String, Int32)] = state.dailyData.reversed().map { (daily) in
            (daily.formattedDate(), daily.positiveIncrease)
        }
        return ChartData(values: values)
    }
    
    func deathsChartData() -> ChartData {
        let values: [(String, Int32)] = state.dailyData.reversed().map { (daily) in
            (daily.formattedDate(), daily.deathIncrease)
        }
        return ChartData(values: values)
    }
}

struct StateDailyCasesChart_Previews: PreviewProvider {
    static var previews: some View {
        StateChartsView(state: StateData(state: "MN", dailyData: []))
    }
}
