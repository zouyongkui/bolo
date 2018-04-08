$(function()
{
	
	/*
	define a new language named "custom"
	*/

	$.dateRangePickerLanguages['custom'] = 
	{
		'selected': 'Choosed:',
		'days': 'Days',
		'apply': 'Close',
		'week-1' : 'Mon',
		'week-2' : 'Tue',
		'week-3' : 'Wed',
		'week-4' : 'Thu',
		'week-5' : 'Fri',
		'week-6' : 'Sat',
		'week-7' : 'Sun',
		'month-name': ['January','February','March','April','May','June','July','August','September','October','November','December'],
		'shortcuts' : 'Shortcuts',
		'past': 'Past',
		'7days' : '7days',
		'14days' : '14days',
		'30days' : '30days',
		'previous' : 'Previous',
		'prev-week' : 'Week',
		'prev-month' : 'Month',
		'prev-quarter' : 'Quarter',
		'prev-year' : 'Year',
		'less-than' : 'Date range should longer than %d days',
		'more-than' : 'Date range should less than %d days',
		'default-more' : 'Please select a date range longer than %d days',
		'default-less' : 'Please select a date range less than %d days',
		'default-range' : 'Please select a date range between %d and %d days',
		'default-default': 'This is costom language'
	};
	
//	$('#date-range0').dateRangePicker();
//	$('#date-range1').dateRangePicker(
//	{
//		startOfWeek: 'monday',
//  	separator : ' ~ ',
//  	format: 'DD.MM.YYYY HH:mm',
//  	autoClose: false,
//		time: {
//			enabled: true
//		}
//	});
	
//	$('#date-range2').dateRangePicker();

//	$('#date-range3').dateRangePicker(
//	{
//		language:'cn'
//	});
//
//	$('#date-range4').dateRangePicker(
//	{
//		language:'en'
//	});
//
//	$('#date-range105').dateRangePicker(
//	{
//		showCustomValues: true,
//		customValueLabel: 'Dynamic Ranges',
//		customValues:
//		[
//			{
//				name: 'MTD',
//				value: 'Month To Date'
//			},
//			{
//				name: 'YTD',
//				value: 'Year To Date'
//			}
//		]
//	})

/*
	$('#date-range100').dateRangePicker(
	{
		shortcuts : null,
		startOfWeek: 'sunday',
		language:'en',
		customShortcuts: 
		[
			{
				name: 'this week',
				dates : function()
				{
					var start = moment().day(0).toDate();
					var end = moment().day(6).toDate();
					// start.setDate(1);
					// end.setDate(30);
					return [start,end];
				}
			},
			//if only return an array of one date, it will display the month which containing the date. and it will not select any date range
			{
				name: 'Oct 2014',
				dates : function()
				{
					//move calendars to show this date's month and next month
					var movetodate = moment('2014-10','YYYY-MM').toDate();
					return [movetodate];
				}
			}
		]
	}).bind('datepicker-apply',function(event,obj)
	{
		console.log(obj);
	});
*/

//	$('#date-range101').dateRangePicker(
//	{
//		shortcuts : 
//		{
//			'next-days': [3,5,7],
//			'next': ['week','month','year']
//		}
//	});

	$('#date-range102').dateRangePicker(
	{
		shortcuts : 
		{
			'prev-days': [3,5,7],
			'prev': ['week','month','year'],
			'next-days':null,
			'next':null
		}
	});






});
